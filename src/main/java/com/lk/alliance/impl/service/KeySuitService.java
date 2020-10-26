package com.lk.alliance.impl.service;

import com.lk.alliance.domain.vo.KeySuitVO;
import com.lk.alliance.service.IKeySuitService;
import com.xiaoleilu.hutool.crypto.asymmetric.RSA;

/**
 * @Description 公钥密钥生成Service.
 * @Date 2020/7/8
 * @Author lk
 */
public class KeySuitService implements IKeySuitService {

    /**
     * 返回公私密钥对.
     * @return 密钥对
     **/
    @Override
    public KeySuitVO getRandowKeySuit() {
        final RSA rsa = new RSA();

        //私钥
        final String privateKeyBase64 = rsa.getPrivateKeyBase64();
        //公钥
        final String publicKeyBase64 = rsa.getPublicKeyBase64();

        final KeySuitVO keySuitVO = new KeySuitVO();
        keySuitVO.setPrivateKey(privateKeyBase64);
        keySuitVO.setPublicKey(publicKeyBase64);

        return keySuitVO;
    }
}
