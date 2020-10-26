package com.lk.pbft.tio.peers;

import com.lk.cons.Const;
import com.lk.domain.packet.BlockPacket;
import com.lk.pbft.tio.handler.BlockChainPbftClientAioHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

import javax.annotation.PostConstruct;

/**
 * @Classname BlockChainClient
 * @Description 基于t-io的区块链底层P2P网络平台的客户端
 * @Date 2020/6/3
 * @Author lk
 */
@Component
public class BlockChainPbftClient {
    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(BlockChainPbftClient.class);

    /**
     * 服务端节点.
     **/
    private Node serverNode;

    /**
     * 消息处理.
     **/
    public static BlockChainPbftClientAioHandler aioHandler;

    /**
     * 事件监听器.
     **/
    public static ClientAioListener aioListener = null;

    /**
     * 一组连接共用的上下文对象.
     **/
    public static ClientGroupContext clientGroupContext;

    /**
     * 断链后自动连接.
     * 若不想自动连接设为null
     **/
    public static ReconnConf reconnConf = new ReconnConf(5000L);

    /**
     * TioClient对象.
     **/
    public static TioClient tioClient = null;

    /**
     * 通道上下文.
     **/
    public static ClientChannelContext clientChannelContext = null;

    /**
     * 启动程序入口.
     **/
    @PostConstruct
    @Order(2)
    public void start() {
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("武汉客户端即将启动");
            }

            // 初始化
            serverNode = new Node(Const.SERVER, Const.PORT);
            aioHandler = new BlockChainPbftClientAioHandler();
            clientGroupContext = new ClientGroupContext(aioHandler, aioListener, reconnConf);

            clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
            tioClient = new TioClient(clientGroupContext);
            clientChannelContext = tioClient.connect(serverNode);

            // 连上后，发一条消息测试
            sendMessage();

            if (LOG.isInfoEnabled()) {
                LOG.info("武汉客户端启动完毕");
            }
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                e.getMessage();
            }
        }
    }

    /**
     * 发送消息.
     **/
    private void sendMessage() throws Exception {
        BlockPacket packet = new BlockPacket();
        packet.setBody("tal say hello world to block chain!"
                .getBytes(BlockPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }
}
