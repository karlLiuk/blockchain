package com.lk.util.hash;

import com.xiaoleilu.hutool.crypto.digest.DigestUtil;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Classname SHAUtil
 * @Description SHA-256加密
 * @Date 2020/5/24
 * @Author lk
 */
public class SHAUtil {

    /**
     * @Description
     *
     * @Param [originalStr] 加密前的报文
     * @return java.lang.String 加密后的报文
     **/
    public static String getSHA256BasedMD(String originalStr) {
        /**
         * 消息摘要.
         **/
        MessageDigest messageDigest;

        /**
         * 密文.
         **/
        String encodeStr = "";
        try {
            // 实现了SHA-256加密的消息摘要
            messageDigest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = messageDigest.digest(originalStr.getBytes("UTF-8"));
            encodeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * @Description
     *
     * @Param [originalStr] 加密前的报文
     * @return java.lang.String 加密后的报文
     **/
    public static String getSHA256BasedHutool(String originalStr) {
        return DigestUtil.sha256Hex(originalStr);
    }
}
