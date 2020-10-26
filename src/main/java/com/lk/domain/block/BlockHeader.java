package com.lk.domain.block;

import java.util.List;

/**
 * @Classname BlockHeader
 * @Description 区块头
 * @Date 2020/6/18
 * @Author lk
 */
public class BlockHeader {
    /**
     * 版本号.
     **/
    private int version;

    /**
     * 上一区块的哈希.
     **/
    private String hashPreviousBlock;

    /**
     * Merkle树根节点哈希值.
     **/
    private int hashMerkleRoot;

    /**
     * 生成该区块的公钥.
     **/
    private int publicKey;

    /**
     * 区块的序号.
     **/
    private int number;

    /**
     * 时间戳.
     **/
    private Long timeStamp;

    /**
     * 32位随机数.
     **/
    private long nonce;

    /**
     * 该区块里每条交易信息的哈希集合，按顺序来的.
     * 通过该哈希集合能算出根节点哈希值.
     **/
    private List<String> hashList;

    /**
     * 获取version.
     *
     * @return version
     **/
    public int getVersion() {
        return version;
    }

    /**
     * 设置version.
     *
     * @Param the version to set
     **/
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 获取hashPreviousBlock.
     *
     * @return hashPreviousBlock
     **/
    public String getHashPreviousBlock() {
        return hashPreviousBlock;
    }

    /**
     * 设置hashPreviousBlock.
     *
     * @Param the hashPreviousBlock to set
     **/
    public void setHashPreviousBlock(String hashPreviousBlock) {
        this.hashPreviousBlock = hashPreviousBlock;
    }

    /**
     * 获取hashMerkleRoot.
     *
     * @return hashMerkleRoot
     **/
    public int getHashMerkleRoot() {
        return hashMerkleRoot;
    }

    /**
     * 设置hashMerkleRoot.
     *
     * @Param the hashMerkleRoot to set
     **/
    public void setHashMerkleRoot(int hashMerkleRoot) {
        this.hashMerkleRoot = hashMerkleRoot;
    }

    /**
     * 获取publicKey.
     *
     * @return publicKey
     **/
    public int getPublicKey() {
        return publicKey;
    }

    /**
     * 设置publicKey.
     *
     * @Param the publicKey to set
     **/
    public void setPublicKey(int publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * 获取number.
     *
     * @return number
     **/
    public int getNumber() {
        return number;
    }

    /**
     * 设置number.
     *
     * @Param the number to set
     **/
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 获取timeStamp.
     *
     * @return timeStamp
     **/
    public Long getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置timeStamp.
     *
     * @Param the timeStamp to set
     **/
    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * 获取nonce.
     *
     * @return nonce
     **/
    public long getNonce() {
        return nonce;
    }

    /**
     * 设置nonce.
     *
     * @Param the nonce to set
     **/
    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    /**
     * 获取hashList.
     *
     * @return hashList
     **/
    public List<String> getHashList() {
        return hashList;
    }

    /**
     * 设置hashList.
     *
     * @Param the hashList to set
     **/
    public void setHashList(List<String> hashList) {
        this.hashList = hashList;
    }
}
