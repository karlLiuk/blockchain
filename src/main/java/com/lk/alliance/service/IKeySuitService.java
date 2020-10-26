package com.lk.alliance.service;

import com.lk.alliance.domain.vo.KeySuitVO;

/**
 * @Description 公钥密钥生成Service接口.
 * @Date 2020/7/8
 * @Author lk
 */
public interface IKeySuitService {

    /**
     * 返回公私密钥对.
     * @return 密钥对
     **/
    KeySuitVO getRandowKeySuit();
}
