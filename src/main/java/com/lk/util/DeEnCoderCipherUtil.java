package com.lk.util;

import ch.qos.logback.core.util.StringCollectionUtil;
import org.testng.util.Strings;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @Classname DeEnCoderCipherUtil
 * @Description 基于Cipher实现的加密和解密工具类
 * @Date 2020/1/8
 * @Author lk
 */
public class DeEnCoderCipherUtil {
    // 加密、解密模式
    private final static String CIPHER_MODE = "DES";

    // DES 密钥
    public static String DEFAULT_DES_KEY = "区块链是分布式数据存储、点对点传输、共识机制、加密算法" +
            "等计算机技术的新型应用模式。";
    /**
     * 加密通用方法
     *
     * @Param originalContent 明文
     * @Param key 密钥
     * @return 密文
     **/
    public static String encrypt(String originalContent, String key){
        // 明文或加密密钥为空时
        if (Strings.isNullOrEmpty(originalContent) || Strings.isNullOrEmpty(key)){
            return null;
        }

        // 明文或加密密钥不为空时
        try {
            final byte[] byteContent = encrypt(originalContent.getBytes(), key.getBytes());
            return new BASE64Encoder().encode(byteContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节加密方法
     *
     * @Param originalContent 明文
     * @Param key 加密密钥的byte数组
     * @return 密文的byte数组
     **/
    private static byte[] encrypt(byte[] originalContent, byte[] key) throws Exception {
        // 1.生产可信任的随机数源
        final Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        // 6.返回密文
        return cipher.doFinal(originalContent);
    }

    /**
     * 解密通用方法
     *
     * @Param ciphertext 密文
     * @Param  key 解密密钥
     * @return 明文
     **/
    public static String decrypt(String ciphertext, String key) {
        // 密文或解密密钥为空时
        if (Strings.isNullOrEmpty(ciphertext) || Strings.isNullOrEmpty(key)){
            return null;
        }

        // 密文或解密密钥不为空时
        try {
            final BASE64Decoder decoder = new BASE64Decoder();
            final byte[] bufCiphertext = decoder.decodeBuffer(ciphertext);
            final byte[] contentByte = decrypt(bufCiphertext, key.getBytes());
            return new String(contentByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节解密方法
     *
     * @Param originalContent 密文
     * @Param key 解密密钥的byte数组
     * @return 明文的byte数组
     **/
    private static byte[] decrypt(byte[] bufCiphertext, byte[] key) throws Exception {
        final Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        // 6.返回密文
        return cipher.doFinal(bufCiphertext);
    }

    /*
     * 初始化Cipher对象
     **/
    private static Cipher getCipher(byte[] key, int decryptMode) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        // 1.生产可信任的随机数源
        final SecureRandom secureRandom = new SecureRandom();
        // 2.基于密钥数据创建DESKeySpec对象
        final DESKeySpec desKeySpec = new DESKeySpec(key);
        // 3.创建密钥工厂，将DESKeySpec转换成SecretKey对象来保存对称密钥
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CIPHER_MODE);
        final SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 4.Cipher对象实际完成加密操作，指定其支持指定的加密和解密算法
        final Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        // 5.用密钥初始化Cipher对象，DECRYPT_MODE表示解密模式
        cipher.init(decryptMode, secretKey, secureRandom);
        return cipher;
    }
}
