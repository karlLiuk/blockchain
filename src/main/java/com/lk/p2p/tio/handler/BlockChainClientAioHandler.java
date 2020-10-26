package com.lk.p2p.tio.handler;

import com.lk.domain.packet.BlockPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

/**
 * @Classname BlockChainClientAioHandler
 * @Description 基于t-io的区块链底层P2P网络平台的客户端消息处理Handler
 * @Date 2020/6/2
 * @Author lk
 */
public class BlockChainClientAioHandler extends AbsAioHandle implements ClientAioHandler {

    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(BlockChainClientAioHandler.class);

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
        }
        return;
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
