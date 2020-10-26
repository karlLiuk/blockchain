package com.lk.domain.block;

/**
 * @Classname ContentInfo
 * @Description 交易内容实体
 * @Date 2020/6/18
 * @Author lk
 */
public class ContentInfo {
    /**
     * 新的JSON交易内容.
     **/
    private String jsonContent;

    /**
     * 时间戳.
     **/
    private Long timeStamp;

    /**
     * 交易发起方的公钥.
     **/
    private String publicKey;

    /**
     * 签名.
     **/
    private String sign;

    /**
     * 该操作的哈希.
     **/
    private String hash;

    /**
     * 获取jsonContent.
     *
     * @return jsonContent
     **/
    public String getJsonContent() {
        return jsonContent;
    }

    /**
     * 设置jsonContent.
     *
     * @Param the jsonContent to set
     **/
    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
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
     * 获取publicKey.
     *
     * @return pulibcKey
     **/
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 设置publicKey.
     *
     * @Param the publicKey to set
     **/
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * 获取sign.
     *
     * @return sign
     **/
    public String getSign() {
        return sign;
    }

    /**
     * 设置sign.
     *
     * @Param the sign to set
     **/
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取hash.
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
