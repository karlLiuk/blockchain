package com.lk.pbft.tio.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lk.domain.packet.BlockPacket;
import com.lk.pbft.domain.VoteInfo;
import com.lk.pbft.enums.VoteEnum;
import com.lk.util.hash.SimpleMerkleTreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;

/**
 * @Classname BlockChainClientAioHandler
 * @Description 基于t-io的区块链底层P2P网络平台的客户端消息处理Handler
 * @Date 2020/6/2
 * @Author lk
 */
public class BlockChainPbftClientAioHandler extends AbsPbftAioHandle implements ClientAioHandler {

    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(BlockChainPbftClientAioHandler.class);

    /**
     * 心跳数据包.
     **/
    private static BlockPacket heartbeatPacket = new BlockPacket();

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
                LOG.info("武汉客户端收到消息：" + str);
            }

            // 收到入库的消息则不再发送
            if ("北京服务端开始区块入库啦".equals(str)) {
                return;
            }

            // 发送PBFT投票信息
            // 如果收到的不是Json数据，说明仍在建立连接。
            // 目前连接已经建立完毕，发起投票。
            if (!str.startsWith("{")) {
                sendVoteMessage(channelContext, VoteEnum.PREPREPARE);
                return;
            }

            // 如果是JSON数据，则表明进入了PBFT投票阶段
            final JSONObject json = JSON.parseObject(str);
            if (!json.containsKey("code")) {
                LOG.info("武汉客户端收到非JSON化数据");
            }

            final int code = json.getIntValue("code");
            if (code == VoteEnum.PREPARE.getCode()) {
                // 校验哈希
                final VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
                if (!voteInfo.getHash()
                        .equals(SimpleMerkleTreeUtil.getTreeRootNodeHash(voteInfo.getContents()))) {
                    LOG.info("武汉客户端收到错误的JSON化数据");
                    return;
                }

                // 校验成功，发送下一状态数据
                sendVoteMessage(channelContext, VoteEnum.COMMIT);
            }
        }
        return;
    }

    /**
     * 发起pre-prepare消息给其他节点.
     **/
    private void sendVoteMessage(final ChannelContext channelContext, final VoteEnum voteEnum) throws Exception {
        VoteInfo voteInfo = createVoteInfo(voteEnum);
        BlockPacket blockPacket = new BlockPacket();
        blockPacket.setBody(JSON.toJSONString(voteInfo).getBytes(BlockPacket.CHARSET));
        Tio.send(channelContext, blockPacket);
        LOG.info("武汉客户端发送到服务端的pbft消息：" + JSON.toJSONString(voteInfo));
    }
    /**
     * 如果返回null，则框架层面不会发心跳；
     * 如果返回非null，则框架层面会定时发本方法返回的消息包
     **/
    @Override
    public Packet heartbeatPacket() {
        return heartbeatPacket;
    }
}
