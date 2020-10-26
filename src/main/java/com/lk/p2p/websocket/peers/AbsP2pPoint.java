package com.lk.p2p.websocket.peers;

import com.lk.pbft.domain.VoteInfo;
import com.lk.pbft.enums.VoteEnum;
import com.lk.util.hash.SimpleMerkleTreeUtil;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname AbsP2pPoint
 * @Description P2P节点抽象类
 * @Date 2020/6/1
 * @Author lk
 */
public abstract class AbsP2pPoint {
    /**
     * 日志对象
     **/
    private static final Logger LOG = LoggerFactory.getLogger(AbsP2pPoint.class);

    /**
     * @Description 发送消息
     *
     * @Param [webSocket] 对方节点
     * @Param [message] 消息内容
     * @return void
     **/
    public void sendMessage(final WebSocket webSocket, final String message) {
        if (LOG.isInfoEnabled()) {
            LOG.info("发送给"
                    + webSocket.getRemoteSocketAddress().getPort()
                    + "的p2p消息是：" + message);
        }
        webSocket.send(message);
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
     * 向所有连接到本机的WebSocket广播消息.
     * @param localSockets 所有连接到本机的WebSocket
     * @param message 待广播的消息
     * @return void
     **/
    public void broadcast(final List<WebSocket> localSockets, final String message) {
        if (localSockets.size() == 0 || Strings.isNullOrEmpty(message)) {
            return;
        }
        LOG.info("开始向所有连接广播！");
        for (WebSocket webSocket : localSockets) {
            this.sendMessage(webSocket, message);
        }
        LOG.info("广播结束！");
    }
}
