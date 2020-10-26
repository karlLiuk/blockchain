package com.lk.p2p.websocket.peers;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.testng.util.Strings;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname P2pPointClient
 * @Description 基于Spring Boot 2.0的WebSocket客户端
 * @Date 2020/5/28
 * @Author lk
 */
@Component
public class P2pPointClient extends AbsP2pPoint {
    // 日志记录
    private static final Logger LOG = LoggerFactory.getLogger(P2pPointClient.class);

    // P2P网络中的节点既是服务器，又是客户端。
    // 作为服务器运行在7001端口(P2pPointServer的port字段)
    // 同时作为客户端ws://localhost:7001连接到服务端
    private String wsUrl = "ws://localhost:7001/";

    // 所有客户端WebSocket的连接池缓存
    private List<WebSocket> localSockets = new ArrayList<WebSocket>();

    public List<WebSocket> getLocalSockets() {
        return localSockets;
    }

    public void setLocalSockets(List<WebSocket> localSockets) {
        this.localSockets = localSockets;
    }

    /**
     * 连接到服务端
     **/
    @PostConstruct
    @Order(2)
    public void connectPeer() {
        try {
            final WebSocketClient socketClient = new WebSocketClient(new URI(wsUrl)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    sendMessage(this, "武汉客户端成功创建客户端");

                    localSockets.add(this);
                }

                @Override
                public void onMessage(final String message) {
                    if (LOG.isInfoEnabled()) {
                        LOG.info("武汉客户端收到武汉服务端发送的消息：" + message);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    if (LOG.isInfoEnabled()) {
                        LOG.info("武汉客户端关闭");
                    }
                    localSockets.remove(this);
                }

                @Override
                public void onError(Exception ex) {
                    if (LOG.isInfoEnabled()) {
                        LOG.info("武汉客户端报错");
                    }
                }
            };
            // 客户端开始连接服务端
            socketClient.connect();
        } catch (URISyntaxException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("武汉客户端连接错误：" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * @Description 向服务端发送消息
     *
     * @Param [ws, message]
     * @return void
     **/
    /*private void sendMessage(final WebSocket webSocket, final String message) {
        if (LOG.isInfoEnabled()) {
            LOG.info("发送给"
                    + webSocket.getRemoteSocketAddress().getPort()
                    + "的p2p消息是：" + message);
        }
        webSocket.send(message);
    }*/

    /**
     * @Description 向所有连接到本机的客户端广播消息
     *
     * @Param [message] 待广播内容
     **/
    public void broadcast(String message) {
        if (localSockets.size() == 0 || Strings.isNullOrEmpty(message)) {
            return;
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("开始往服务器发送广播。。。");
        }
        for (WebSocket socket : localSockets) {
            this.sendMessage(socket, message);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("往服务器发送广播结束。。。");
        }
    }
}
