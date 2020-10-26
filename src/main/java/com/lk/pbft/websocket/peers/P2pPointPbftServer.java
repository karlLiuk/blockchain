package com.lk.pbft.websocket.peers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lk.p2p.websocket.peers.AbsP2pPoint;
import com.lk.pbft.domain.VoteInfo;
import com.lk.pbft.enums.VoteEnum;
import com.lk.util.hash.SimpleMerkleTreeUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname P2pPointPbftServer
 * @Description WebSocket服务端
 * @Date 2020/6/14
 * @Author lk
 */
public class P2pPointPbftServer extends AbsP2pPoint {

    /**
     * LOG.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(P2pPointPbftServer.class);

    /**
     * 本机Server的WebSocket端口.
     **/
    private int port = 7001;

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
     * 初始化P2P Server端
     **/
    @PostConstruct
    @Order(1)
    public void initServer() {
        // 初始化WebSocketServer服务端
        final WebSocketServer server = new WebSocketServer(new InetSocketAddress(port)) {

            /**
             * 连接成功时触发.
             * @param webSocket 连接到服务端的webSocket
             * @param clientHandshake
             * @return void
             **/
            @Override
            public void onOpen(final WebSocket webSocket,
                               final ClientHandshake clientHandshake) {
                sendMessage(webSocket, "武汉服务端成功创建连接");

                localSockets.add(webSocket);
            }

            /**
             * 断开连接时触发.
             * @param webSocket 连接到服务端的webSocket
             * @param code
             * @param reason
             * @param remote
             * @return void
             **/
            @Override
            public void onClose(final WebSocket webSocket,
                                final int code, final String reason, final boolean remote) {
                LOG.info(webSocket.getRemoteSocketAddress() +
                        "客户端与服务端断开连接");

                localSockets.remove(webSocket);
            }

            /**
             * 收到客户端发来的消息时触发.
             * @param webSocket 连接到服务端的webSocket
             * @param message 接收的消息
             * @return void
             **/
            @Override
            public void onMessage(final WebSocket webSocket, final String message) {
                LOG.info("武汉服务端接收到客户端消息:" + message);

                //收到入库的消息则不再发送
                if ("武汉客户端开始区块入库啦".equals(message)) {
                    return;
                }

                //如果收到的不是JSON化数据，则说明仍处在双方建立连接的过程中。
                //目前连接已经建立完毕，发起投票
                if (!message.startsWith("{")) {
                    final VoteInfo voteInfo = createVoteInfo(VoteEnum.PREPREPARE);
                    sendMessage(webSocket, JSON.toJSONString(voteInfo));
                    LOG.info("武汉服务端发送到客户端的pbft消息:"
                            + JSON.toJSONString(voteInfo));
                    return;
                }

                //如果收到的是JSON化数据，则表明已经进入到PBFT投票阶段
                final JSONObject jsonObject = JSON.parseObject(message);
                if (!jsonObject.containsKey("code")) {
                    LOG.info("武汉服务端收到非JSON化数据");
                }
                final int code = jsonObject.getIntValue("code");
                if (code == VoteEnum.PREPARE.getCode()) {
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
                final VoteInfo voteInfo = createVoteInfo(VoteEnum.COMMIT);
                sendMessage(webSocket, JSON.toJSONString(voteInfo));
                LOG.info("武汉服务端发送到客户端pbft消息:" +
                        JSON.toJSONString(voteInfo));
            }

            /**
             * 连接发生错误时调用，紧接着触发onClose()方法.
             * @param webSocket 连接到服务端的webSocket
             * @param ex 异常
             * @return void
             **/
            @Override
            public void onError(final WebSocket webSocket, final Exception ex) {
                LOG.info(webSocket.getRemoteSocketAddress() + "客户端连接错误");

                localSockets.remove(webSocket);
            }

            @Override
            public void onStart() {
                LOG.info("武汉的WebSocket Server端启动...");
            }
        };
    }
}
