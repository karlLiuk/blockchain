package com.lk.alliance.domain.vo;

/**
 * @Description 公私钥对.
 * @Date 2020/7/8
 * @Author lk
 */
public class KeySuitVO {

    /**
     * 私钥.
     */
    private String privateKey;

    /**
     * 公钥.
     */
    private String publicKey;

    /**
     * 获取privateKey.
     *
     * @return privateKey
     **/
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * 设置privateKey.
     *
     * @Param the privateKey to set
     **/
    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * 获取publicKey.
     *
     * @return publicKey
     **/
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 设置publicKey.
     *
     * @Param the publicKey to set
     **/
    public void setPublicKey(final String publicKey) {
        this.publicKey = publicKey;
    }
}
