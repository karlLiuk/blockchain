package com.lk.p2p.tio.handler;

import com.lk.domain.packet.BlockPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

/**
 * @Classname BlockChainServerAioHandle
 * @Description 基于t-io的区块链底层P2P网络平台的服务端消息处理Handler
 * @Date 2020/6/2
 * @Author lk
 */
public class BlockChainServerAioHandler extends AbsAioHandle implements ServerAioHandler {

    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(BlockChainServerAioHandler.class);

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
            final BlockPacket respPacket = new BlockPacket();
            respPacket.setBody(("武汉服务端收到了你的消息，你的消息是：" + str).getBytes(BlockPacket.CHARSET));
            Tio.send(channelContext, respPacket);
        }
        return;
    }
}
