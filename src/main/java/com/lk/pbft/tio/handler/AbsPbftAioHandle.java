package com.lk.pbft.tio.handler;

import com.lk.domain.packet.BlockPacket;
import com.lk.pbft.domain.VoteInfo;
import com.lk.pbft.enums.VoteEnum;
import com.lk.util.hash.SimpleMerkleTreeUtil;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname AbsAioHandle
 * @Description 基于t-io的消息处理抽象类
 * @Date 2020/6/8
 * @Author lk
 */
public abstract class AbsPbftAioHandle implements AioHandler {

    /**
     * @Description 解码： 把接收到的ByteBuffer解码成应用可以识别的业务消息包
     *      *              消息结构： 消息头 + 消息体
     *      *              消息头结构： 4个字节，存储消息体的长度
     *      *              消息体结构： 对象的Json串的byte[]
     * @Param [byteBuffer, limit, position, readableLength, channelContext]
     * @return org.tio.core.intf.Packet
     **/
    @Override
    public Packet decode(final ByteBuffer byteBuffer, final int limit,
                         final int position, final int readableLength, final ChannelContext channelContext)
            throws AioDecodeException {
        // 收到的消息长度不够无法组成数据包BlockPacket
        if (readableLength < BlockPacket.HEADER_LENGTH) {
            return null;
        }
        // 读取消息体长度
        final int bodyLength = byteBuffer.getInt();
        // 数据不正确
        if (bodyLength < 0 ) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right," +
                    " remote:" + channelContext.getClientNode());
        }
        // 本次需要的数据长度
        final int neededLength = BlockPacket.HEADER_LENGTH + bodyLength;
        // 收到的数据是否足够组包
        final int isDataEnough = readableLength - neededLength;
        if (isDataEnough < 0) {
            return null;
        } else {
            // 组包成功
            BlockPacket imPacket = new BlockPacket();
            if (bodyLength > 0) {
                final byte[] dst = new byte[bodyLength];
                byteBuffer.get(dst);
                imPacket.setBody(dst);
            }
            return imPacket;
        }
    }

    /**
     * @Description 编码: 把业务消息编码为可以发送的ByteBuffer
     *
     * @Param [packet, groupContext, channelContext]
     * @return java.nio.ByteBuffer
     **/
    @Override
    public ByteBuffer encode(final Packet packet, final GroupContext groupContext,
                             final ChannelContext channelContext) {
        final BlockPacket blockPacket = (BlockPacket) packet;
        final byte[] body = blockPacket.getBody();
        int bodyLength = 0;
        if (body != null) {
            bodyLength = body.length;
        }

        final int totalLength = BlockPacket.HEADER_LENGTH + bodyLength;
        // 创建一个新的byteBuffer
        final ByteBuffer byteBuffer = ByteBuffer.allocate(totalLength);
        // 设置字节序
        byteBuffer.order(groupContext.getByteOrder());
        // 写入消息头
        byteBuffer.putInt(bodyLength);
        // 写入消息体
        if (body != null) {
            byteBuffer.put(body);
        }
        return byteBuffer;
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

}
