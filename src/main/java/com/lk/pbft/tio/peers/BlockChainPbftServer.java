package com.lk.pbft.tio.peers;

import com.lk.cons.Const;
import com.lk.pbft.tio.handler.BlockChainPbftServerAioHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioListener;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @Classname BlockChainServer
 * @Description 基于t-io的区块链底层P2P网络平台的服务端
 * @Date 2020/6/3
 * @Author lk
 */
@Component
public class BlockChainPbftServer {
    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(BlockChainPbftServer.class);

    /**
     * 消息处理.
     **/
    public static BlockChainPbftServerAioHandler aioHandler = new BlockChainPbftServerAioHandler();

    /**
     * 事件监听器.
     **/
    public static ServerAioListener aioListener = null;

    /**
     * 一组连接共用的上下文对象.
     **/
    public static ServerGroupContext serverGroupContext =
            new ServerGroupContext("hello-tio-server", aioHandler, aioListener);

    /**
     * tioServer对象.
     **/
    public static TioServer tioServer = new TioServer(serverGroupContext);

    /**
     * 有时候需要绑定IP，不需要则为null
     **/
    public static String serverIp = null;//Const.SERVER

    /**
     * 监听的端口.
     **/
    public static int serverPort = Const.PORT;

    @PostConstruct
    @Order(1)
    public void start() {
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("武汉服务端即将启动");
            }

            serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
            tioServer.start(serverIp, serverPort);

            if (LOG.isInfoEnabled()) {
                LOG.info("武汉服务端启动完毕");
            }
        } catch (IOException e) {
            if (LOG.isErrorEnabled()) {
                e.getMessage();
            }
        }
    }
}
