package com.lk.domain.block;

import com.xiaoleilu.hutool.crypto.digest.DigestUtil;

/**
 * @Classname Block
 * @Description 区块.
 * @Date 2020/6/18
 * @Author lk
 */
public class Block {
    /**
     * 区块头.
     **/
    private BlockHeader blockHeader;

    /**
     * 区块body.
     **/
    private BlockBody blockBody;

    /**
     * 该区块的哈希.
     **/
    private String blockHash;

    /**
     * 获取blockHeader.
     *
     * @return blockHeader
     **/
    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    /**
     * 设置blockHeader.
     *
     * @Param the blockHeader to set
     **/
    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    /**
     * 获取blockBody.
     *
     * @return blockBody
     **/
    public BlockBody getBlockBody() {
        return blockBody;
    }

    /**
     * 设置blockBody.
     *
     * @Param the blockBody to set
     **/
    public void setBlockBody(BlockBody blockBody) {
        this.blockBody = blockBody;
    }

    /**
     * 获取blockHash.
     * 根据该区块的所有属性计算SHA256
     * @return blockHash
     **/
    public String getBlockHash() {
        return DigestUtil.sha256Hex(blockHeader.toString() + blockBody.toString());
    }

    /**
     * 设置blockHash.
     *
     * @Param the blockHash to set
     **/
    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }
}
