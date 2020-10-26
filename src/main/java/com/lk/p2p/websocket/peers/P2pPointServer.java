package com.lk.p2p.websocket.peers;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.testng.util.Strings;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname P2pPointServer
 * @Description 基于Spring Boot 2.0的WebSocket服务端
 * @Date 2020/5/28
 * @Author lk
 */
@Component
public class P2pPointServer extends AbsP2pPoint {
    // 日志记录
    private static final Logger LOG = LoggerFactory.getLogger(P2pPointServer.class);

    // 本机Server的WebSocket端口
    // 多机测试时可改变该值
    private int port = 7001;

    // 所有连接到服务端的WebSocket缓存器
    private List<WebSocket> localSockets = new ArrayList<WebSocket>();

    public List<WebSocket> getLocalSockets() {
        return localSockets;
    }

    /**
     * 初始化P2p Server端.
     **/
    @PostConstruct
    @Order(1)
    public void initServer() {
        /**
         * @Description 初始化WebSocket的服务端定义内部类对象socketServer，
         * 源于WebSocketServer
         * 
         * @Param [] InetSocketAddress是（IP地址 + 端口号）类型，即端口地址类型
         * @return void
         **/
        final WebSocketServer socketServer = new WebSocketServer(new InetSocketAddress(port)) {
            /**
             * @Description 创建连接成功时触发
             *
             * @Param [webSocket, clientHandshake]
             * @return void
             **/
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
                sendMessage(webSocket, "武汉服务端成功创建连接");

                // 当成功创建一个WebSocket连接时，将该连接加入连接池
                localSockets.add(webSocket);
            }

            /**
             * @Description 断开连接时触发
             * 
             * @Param [webSocket, i, s, b]
             * @return void
             **/
            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {
                if (LOG.isInfoEnabled()) {
                    LOG.info(webSocket.getRemoteSocketAddress() + "客户端与服务器断开连接！");
                }

                // 当客户端断开连接时，WebSocket连接池删除该连接
                localSockets.remove(webSocket);
            }

            /**
             * @Description 收到客户端发来的消息时触发
             *
             * @Param [webSocket, s]
             * @return void
             **/
            @Override
            public void onMessage(WebSocket webSocket, String msg) {
                if (LOG.isInfoEnabled()) {
                    LOG.info("武汉服务端收到客户端消息：" + msg);
                }
                sendMessage(webSocket, "收到消息");
            }

            /**
             * @Description 连接发生错误时调用，紧接着触发onClose方法
             * 
             * @Param [webSocket, e]
             * @return void
             **/
            @Override
            public void onError(WebSocket webSocket, Exception e) {
                if (LOG.isInfoEnabled()) {
                    LOG.info(webSocket.getRemoteSocketAddress() + "客户端连接错误！");
                }
                localSockets.remove(webSocket);
            }

            @Override
            public void onStart() {
                if (LOG.isInfoEnabled()) {
                    LOG.info("武汉的WebSocket Server端启动。。。");
                }
            }
        };

        socketServer.start();
        if (LOG.isInfoEnabled()) {
            LOG.info("武汉服务器监听socketServer端口：" + port);
        }
    }

    /**
     * @Description 向所有连接到本机的客户端广播消息
     * 
     * @Param [webSocket, message]
     * @return void
     **/
    /*public void sendMessage(final WebSocket webSocket, final String message) {
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
            LOG.info("开始发送广播。。。");
        }
        for (WebSocket socket : localSockets) {
            this.sendMessage(socket, message);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("发送广播结束。。。");
        }
    }
}
