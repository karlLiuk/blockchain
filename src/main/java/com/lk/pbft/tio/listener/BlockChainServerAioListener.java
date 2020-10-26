package com.lk.pbft.tio.listener;

import org.tio.core.ChannelContext;
import org.tio.core.intf.AioListener;
import org.tio.core.intf.Packet;

/**
 * @Classname BlockChainServerAioListener
 * @Description 基于t-io的区块链底层P2P网络平台的服务端监听Listener
 * @Date 2020/6/3
 * @Author lk
 */
public class BlockChainServerAioListener implements AioListener {
    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1) throws Exception {

    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int i) throws Exception {

    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int i) throws Exception {

    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b) throws Exception {

    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long l) throws Exception {

    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) throws Exception {

    }
}
