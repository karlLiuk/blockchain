package com.lk.domain.packet;

import org.tio.core.intf.Packet;
/**
 * @Classname BlockHelloPacket
 * @Description 区块链底层定制的数据包Packet
 * @Date 2020/6/2
 * @Author lk
 */
public class BlockPacket extends Packet {

    /**
     * 序列号.
     **/
    private static final long serialVersionUID = 3017533469262410878L;

    /**
     * 消息头长度.
     **/
    public static final int HEADER_LENGTH = 4;

    /**
     * 字符编码类型.
     **/
    public static final String CHARSET = "utf-8";

    /**
     * 传输内容字节数组.
     **/
    private byte[] body;

    /**
     * 获取传输内容.
     * @return byte[] 传输内容字节数组
     **/
    public byte[] getBody() {
        return body;
    }

    /**
     * 获取传输内容.
     *
     * @Param [body] 传输内容字节数组
     **/
    public void setBody(byte[] body) {
        this.body = body;
    }
}
