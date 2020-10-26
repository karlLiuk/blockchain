package com.lk.util;

import com.google.crypto.tink.*;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;
import com.google.crypto.tink.integration.awskms.AwsKmsClient;
import com.google.crypto.tink.integration.gcpkms.GcpKmsClient;
import com.google.crypto.tink.signature.PublicKeySignFactory;
import com.google.crypto.tink.signature.PublicKeyVerifyFactory;
import com.google.crypto.tink.signature.SignatureKeyTemplates;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @Classname TinkUtil
 * @Description 基于Tink工具类的加密解密类
 * @Date 2020/1/12
 * @Author lk
 * Tink通过原语执行加密任务，每个原语都是通过指定原语功能的相应接口定义的
 */
public class TinkUtil {
    /**
     * @Description 初始化Tink，注册原语
     *
     * @Param []
     * @return void
     **/
    public void register(){
        try{
            //使用所有原语
            TinkConfig.register();

            // AEAD原语（带有关联数据的认证加密）
            //AeadConfig.register();

            // 自定义初始化
            //Registry.registerKeyManager(new MyAeadKeyManager());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Description 使用AEAD原语加密/解密
     *
     * @Param plainText 明文
     * @Param associatedData 额外的AEAD输入
     * @return void
     **/
    public void aead(byte[] plainText, byte[] associatedData) {
        try {
            // 1.配置生成密钥集
            KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

            // 2.使用key获取所选原语的实例
            Aead aead = AeadFactory.getPrimitive(keysetHandle);

            // 3.使用原语完成加密任务
            byte[] cipherText = aead.encrypt(plainText, associatedData);
            
            // 解密密文
            byte[] decrypted = aead.decrypt(cipherText, associatedData);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 保存生成的密钥到文件中
     * @return void
     **/
    public void save2File() {
        try {
            // 生成AES对应的新密钥集
            final KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

            // 写入json文件
            final String keysetFileName = "my_keyset.json";
            final KeysetWriter keysetWriter = JsonKeysetWriter.withFile(new File(keysetFileName));
            CleartextKeysetHandle.write(keysetHandle, keysetWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 保存生成的密钥到文件中(使用Google Cloud KMS key来对key加密)
     * @return void
     **/
    public void save2FileBaseKMS() {
        try {
            // 生成AES对应的新密钥集
            final KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

            // 写入json文件
            final String keysetFileName = "my_keyset.json";
            // 使用gcp-kms方式对密钥加密
            final String masterKeyUri = "gcp-kms://projects/thik-examples/locations/global/keyRings/foo/cryptoKeys/bar";
            final KeysetWriter keysetWriter = JsonKeysetWriter.withFile(new File(keysetFileName));
            keysetHandle.write(keysetWriter, new GcpKmsClient().getAead(masterKeyUri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 加载加密的密钥集
     * @return com.google.crypto.tink.KeysetHandle 加密的密钥集
     **/
    public KeysetHandle loadKeySet() {
        try {
            final String keysetFileName = "my_keyset.json";
            // 使用aws-kms方式对密钥加密
            final String masterKeyUri =
                    "aws-kms://arn:aws:kms:us-east-1:007084425826:key/84a65985-f868-4bfc-83c2-366618acf147";
            final JsonKeysetReader keysetReader = JsonKeysetReader.withFile(new File(keysetFileName));
            final KeysetHandle keysetHandle = KeysetHandle.read(keysetReader,
                    new AwsKmsClient().getAead(masterKeyUri));
            return keysetHandle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 加载明文的密钥集
     * @return com.google.crypto.tink.KeysetHandle 加密的密钥集
     **/
    public KeysetHandle loadClearTextKeySet() {
        try {
            final String keysetFileName = "my_keyset.json";

            final JsonKeysetReader keysetReader = JsonKeysetReader.withFile(new File(keysetFileName));
            final KeysetHandle keysetHandle = CleartextKeysetHandle.read(keysetReader);
            return keysetHandle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 签名/签名验证
     *
     * @Param [data]
     * @return void
     **/
    public void signatures(byte[] data) {
        try {
            // 1.ESCSA对应的KeysetHandle对象
            final KeysetHandle privateKeysetHandle = KeysetHandle
                    .generateNew(SignatureKeyTemplates.ECDSA_P256);
            // 2.获取私钥
            final PublicKeySign signer = PublicKeySignFactory.getPrimitive(privateKeysetHandle);

            // 3.用私钥签名
            final byte[] signature = signer.sign(data);

            // 签名验证
            // 1.获取公钥对应的KeysetHandle对象
            final KeysetHandle publicKeysetHandle = privateKeysetHandle.getPublicKeysetHandle();
            // 2.根据公钥获取验证的私钥
            final PublicKeyVerify verifier = PublicKeyVerifyFactory.getPrimitive(publicKeysetHandle);
            // 3.使用私钥校验签名
            verifier.verify(signature, data);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
