package com.lk.pbft.websocket.peers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lk.p2p.websocket.peers.AbsP2pPoint;
import com.lk.pbft.domain.VoteInfo;
import com.lk.pbft.enums.VoteEnum;
import com.lk.util.hash.SimpleMerkleTreeUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname P2pPointPbftServer
 * @Description WebSocket客户端
 * @Date 2020/6/14
 * @Author lk
 */
public class P2pPointPbftClient extends AbsP2pPoint {

    /**
     * LOG.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(P2pPointPbftClient.class);

    /**
     * 连接的服务端地址.
     **/
    private String wsUrl = "ws://localhost:7001/";

    /**
     * 所有连接到服务端的WebSocket缓存器.
     **/
    private List<WebSocket> localSockets = new ArrayList<>();

    /**
     *  获取所有连接到服务端的WebSocket.
     * @return 集合
     **/
    private List<WebSocket> getLocalSockets() {
        return localSockets;
    }

    /**
     * 设置所有连接到服务端的WebSocket.
     * @param localSockets 集合
     * @return void
     **/
    public void setLocalSockets(final List<WebSocket> localSockets) {
        this.localSockets = localSockets;
    }

    /**
     * 连接到服务端.
     **/
    @PostConstruct
    @Order(2)
    public void coectPeer() {
        try {
            // 初始化WebSocketServer服务端
            final WebSocketClient client = new WebSocketClient(new URI(wsUrl)) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    sendMessage(this, "武汉客户端创建成功");

                    localSockets.add(this);
                }

                @Override
                public void onMessage(String message) {
                    LOG.info("武汉客户端收到武汉服务端发送的消息：" + message);

                    //如果收到的不是JSON化数据，则说明不是PBFT阶段
                    if (!message.startsWith("{")) {
                        return;
                    }

                    //如果收到的是JSON化数据，则表明已经进入到PBFT投票阶段
                    final JSONObject jsonObject = JSON.parseObject(message);
                    if (!jsonObject.containsKey("code")) {
                        LOG.info("武汉客户端收到非JSON化数据");
                    }
                    final int code = jsonObject.getIntValue("code");
                    if (code == VoteEnum.PREPREPARE.getCode()) {
                        //校验哈希
                        final VoteInfo voteInfo = JSON.parseObject(message, VoteInfo.class);
                        if (!voteInfo.getHash()
                                .equals(SimpleMerkleTreeUtil
                                        .getTreeRootNodeHash(voteInfo.getContents()))) {
                            LOG.info("武汉服务端收到错误的JSON化数据");
                            return;
                        }
                    }

                    // 校验成功，发送下一个状态的数据
                    final VoteInfo voteInfo = createVoteInfo(VoteEnum.PREPARE);
                    sendMessage(this, JSON.toJSONString(voteInfo));
                    LOG.info("武汉客户端发送到服务端pbft消息:" +
                            JSON.toJSONString(voteInfo));

                    //校验成功，检验节点确认个数是否有效
                    if (getConnectedNodeCount() >= getLeastNodeCount()) {
                        sendMessage(this, "武汉客户端开始区块入库啦");
                        LOG.info("武汉客户端开始区块入库啦");
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    LOG.info("武汉客户端关闭");

                    localSockets.remove(this);
                }

                @Override
                public void onError(Exception ex) {
                    LOG.info("武汉客户端报错");

                    localSockets.remove(this);
                }
            };

            //开始连接服务端
            client.connect();
        } catch (URISyntaxException e) {
            LOG.info("武汉连接错误：" + e.getMessage());
        }
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
