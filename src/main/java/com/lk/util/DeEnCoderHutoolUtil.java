package com.lk.util;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.crypto.asymmetric.KeyType;
import com.xiaoleilu.hutool.crypto.asymmetric.RSA;
import com.xiaoleilu.hutool.crypto.symmetric.DES;
import com.xiaoleilu.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.testng.util.Strings;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Classname DeEnCoderHutoolUtil
 * @Description 基于Hutool工具类的加密解密类
 * @Date 2020/1/9
 * @Author lk
 */
public class DeEnCoderHutoolUtil {
    // 构建RSA对象
    private static RSA rsa = new RSA();
    // 获取私钥
    private static PrivateKey privateKey = rsa.getPrivateKey();
    // 获取公钥
    private static PublicKey publicKey = rsa.getPublicKey();

    /**
     * @Description RSA加密通用方法：对称加密解密
     *
     * @Param [originalContent] 明文
     * @return 密文
     **/
    public static String rsaEncrypt(String originalContent){
        // 明文为空时
        if (Strings.isNullOrEmpty(originalContent)) {
            return null;
        }
        // 公钥加密，之后私钥解密
        return rsa.encryptStr(originalContent, KeyType.PublicKey);
    }

    /**
     * @Description RSA解密通用方法：对称加密解密
     *
     * @Param [cipherText] 密文
     * @return 明文
     **/
    public static String rsaDecrypt(String cipherText) {
        // 密文为空
        if (Strings.isNullOrEmpty(cipherText)){
            return null;
        }
        return rsa.decryptStr(cipherText, KeyType.PrivateKey);
    }

    /**
     * @Description DES加密通用方法：对称加密解密
     *
     * @Param [originalContent] 明文
     * @Param [key] DES加密密钥
     * @return 密文
     **/
    public static String desEncrypt(String originalContent, String key){
        // 明文或加密密钥为空时
        if (Strings.isNullOrEmpty(originalContent) || Strings.isNullOrEmpty(key)) {
            return null;
        }

        // 也可以生产随机密钥
        //byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();

        // 构建DES
        DES des = SecureUtil.des(key.getBytes());
        // 公钥加密，之后私钥解密
        return des.encryptHex(originalContent);
    }

    /**
     * @Description DES解密通用方法：对称加密解密
     *
     * @Param [cipherText] 密文
     * @Param [key] DES解密密钥
     * @return 明文
     **/
    public static String desDecrypt(String cipherText, String key) {
        // 密文或解密密钥为空
        if (Strings.isNullOrEmpty(cipherText) || Strings.isNullOrEmpty(key)){
            return null;
        }

        // 构建DES
        DES des = SecureUtil.des(key.getBytes());
        // 解密
        return des.decryptStr(cipherText);
    }
}
