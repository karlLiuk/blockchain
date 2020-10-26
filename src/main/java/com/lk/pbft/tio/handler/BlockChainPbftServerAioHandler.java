package com.lk.pbft.tio.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lk.domain.packet.BlockPacket;
import com.lk.pbft.domain.VoteInfo;
import com.lk.pbft.enums.VoteEnum;
import com.lk.util.hash.SimpleMerkleTreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname BlockChainServerAioHandle
 * @Description 基于t-io的区块链底层P2P网络平台的服务端消息处理Handler
 * @Date 2020/6/2
 * @Author lk
 */
public class BlockChainPbftServerAioHandler extends AbsPbftAioHandle implements ServerAioHandler {

    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(BlockChainPbftServerAioHandler.class);

    /**
     * @Description 处理消息
     *
     * @Param [packet, channelContext]
     * @return void
     **/
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        final BlockPacket blockPacket = (BlockPacket) packet;
        final byte[] body = blockPacket.getBody();
        if (body != null) {
            String str = new String(body, BlockPacket.CHARSET);
            if (LOG.isInfoEnabled()) {
                LOG.info("武汉服务端收到消息：" + str);
            }
            //如果收到的不是JSON化数据，则说明仍处在双方建立连接的过程中。
            //目前连接已经建立完毕，发起投票
            if (!str.startsWith("{")) {
                BlockPacket respPacket = new BlockPacket();
                respPacket.setBody(("武汉服务端收到客户端的消息，客户端的消息是:"
                        + str).getBytes(BlockPacket.CHARSET));
                return;
            }

            //如果收到的是JSON化数据，则表明已经进入到PBFT投票阶段
            final JSONObject jsonObject = JSON.parseObject(str);
            if (!jsonObject.containsKey("code")) {
                LOG.info("武汉服务端收到非JSON化数据");
            }
            final int code = jsonObject.getIntValue("code");
            if (code == VoteEnum.PREPREPARE.getCode()) {
                //校验哈希
                final VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
                if (!voteInfo.getHash()
                        .equals(SimpleMerkleTreeUtil
                                .getTreeRootNodeHash(voteInfo.getContents()))) {
                    LOG.info("武汉服务端收到错误的JSON化数据");
                    return;
                }
                // 校验成功，发送下一个状态的数据
                final VoteInfo voteInfo2 = createVoteInfo(VoteEnum.PREPARE);
                BlockPacket respPacket = new BlockPacket();
                respPacket.setBody(JSON.toJSONString(voteInfo2).getBytes(BlockPacket.CHARSET));

                Tio.send(channelContext, respPacket);
                LOG.info("武汉服务端发送到武汉客户端的PBFT消息：" + JSON.toJSONString(voteInfo2));
                return;
            }
            if (code == VoteEnum.COMMIT.getCode()) {
                //校验哈希
                final VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
                if (!voteInfo.getHash()
                        .equals(SimpleMerkleTreeUtil
                                .getTreeRootNodeHash(voteInfo.getContents()))) {
                    LOG.info("武汉服务端收到错误的JSON化数据");
                    return;
                }

                //校验成功，检验节点确认个数是否有效
                if (getConnectedNodeCount() >= getLeastNodeCount()) {
                    BlockPacket respPacket = new BlockPacket();
                    respPacket.setBody(("武汉服务端开始区块入库啦"
                            + str).getBytes(BlockPacket.CHARSET));
                    Tio.send(channelContext, respPacket);
                    LOG.info("武汉客户端开始区块入库啦");
                }
            }
        }
        return;
    }

    /**
     * 根据VoteEnum构建对应状态的VoteInfo.
     * @param voteEnum 投票状态
     * @return 投票实体
     **/
    public VoteInfo createVoteInfo(final VoteEnum voteEnum) {
        VoteInfo voteInfo = new VoteInfo();
        voteInfo.setCode(voteEnum.getCode());

        // 内容集合
        List<String> list = new ArrayList<>();
        list.add("AI");
        list.add("BlockChain");

        voteInfo.setContents(list);
        voteInfo.setHash(SimpleMerkleTreeUtil.getTreeRootNodeHash(list));

        return voteInfo;
    }

    /**
     * 计算PBFT消息节点最少确认个数.
     * @return int 个数
     **/
    private int getLeastNodeCount() {
        //测试固定为1
        // 实际部署多个节点时，PBFT算法中拜占庭节点数量f
        // 总结点数3f+1
        return 1;
    }

    /**
     * 已连接节点个数.
     * @return int 个数
     **/
    private int getConnectedNodeCount() {
        //测试固定为1，实际部署多个节点时，按实际情况返回
        return 1;
    }
}
