package com.lk.pbft.domain;

import java.util.List;

/**
 * @Classname VoteInfo
 * @Description 投票实体类
 * @Date 2020/6/11
 * @Author lk
 */
public class VoteInfo {
    /**
     * 投票状态码.
     **/
    private int code;

    /**
     * 待写入区块的内容.
     **/
    private List<String> contents;

    /**
     * 待写入区块的内容的Merkle树根节点的哈希值.
     **/
    private String hash;

    /**
     * 返回code.
     *
     * @return code
     **/
    public int getCode() {
        return code;
    }

    /**
     * 设置code.
     *
     * @Param the code to set
     **/
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回contents.
     *
     * @return contents
     **/
    public List<String> getContents() {
        return contents;
    }

    /**
     * 设置contents.
     *
     * @Param the contents to set
     **/
    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    /**
     * 返回hash.
     *
     * @return hash
     **/
    public String getHash() {
        return hash;
    }

    /**
     * 设置hash.
     *
     * @Param the hash to set
     **/
    public void setHash(String hash) {
        this.hash = hash;
    }
}
