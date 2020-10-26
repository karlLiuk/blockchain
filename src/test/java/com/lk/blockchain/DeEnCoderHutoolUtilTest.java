package com.lk.blockchain;

import com.lk.util.DeEnCoderHutoolUtil;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * @Classname DeEnCoderHutoolUtilTest
 * @Description 基于Hutool工具的加密解密单元测试类
 * @Date 2020/5/20
 * @Author lk
 */
public class DeEnCoderHutoolUtilTest {

    /**
     * @Description 测试DES加密
     * @return void
     **/
    @Test
    public void testDesEncrypt() {
        // case1:明文 null，密钥 null
        String originalContent = null;
        String key = null;
        Assert.assertEquals(DeEnCoderHutoolUtil.desEncrypt(originalContent, key),
                null);
        // case2:明文 not null，密钥 null
        originalContent = "测试Hutool加密解密";
        Assert.assertEquals(DeEnCoderHutoolUtil.desEncrypt(originalContent, key),
                null);
        // case3:明文 null，密钥 not null
        originalContent = null;
        key = "加密解密密钥";
        Assert.assertEquals(DeEnCoderHutoolUtil.desEncrypt(originalContent, key),
                null);
        // case4:明文 not null，密钥 not null
        originalContent = "测试Hutool加密解密";
        key = new String(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded());
        System.out.println(DeEnCoderHutoolUtil.desEncrypt(originalContent, key));
        Assert.assertNotNull(DeEnCoderHutoolUtil.desEncrypt(originalContent, key));
    }

    /**
     * @Description 测试DES解密
     * @return void
     **/
    @Test
    public void testDesDecrypt() {
        // case1:密文 null，密钥 null
        String cipherText = null;
        String key = null;
        Assert.assertEquals(DeEnCoderHutoolUtil.desDecrypt(cipherText, key),
                null);
        // case2:密文 not null，密钥 null
        String originalContent = "测试Hutool加密解密";
        String keyTmp = new String(SecureUtil.
                generateKey(SymmetricAlgorithm.DES.getValue())
                .getEncoded());
        cipherText = DeEnCoderHutoolUtil.desEncrypt(originalContent, keyTmp);
        Assert.assertEquals(DeEnCoderHutoolUtil.desDecrypt(cipherText, key), null);
        // case3:密文 null，密钥 not null
        cipherText = null;
        key = new String(SecureUtil.
                generateKey(SymmetricAlgorithm.DES.getValue())
                .getEncoded());
        Assert.assertEquals(DeEnCoderHutoolUtil.desDecrypt(cipherText, key), null);
        // case4:密文 not null，密钥 not null
        cipherText = DeEnCoderHutoolUtil.desEncrypt(originalContent, key);
        System.out.println(DeEnCoderHutoolUtil.desDecrypt(cipherText, key));
        Assert.assertNotNull(DeEnCoderHutoolUtil.desDecrypt(cipherText, key));
    }
}
