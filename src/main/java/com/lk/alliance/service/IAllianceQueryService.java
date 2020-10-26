package com.lk.alliance.service;

import com.lk.alliance.domain.model.Alliance;

import java.util.List;

/**
 * @Description 联盟成员查询Service接口.
 * @Date 2020/7/7
 * @Author lk
 */
public interface IAllianceQueryService {

    /**
     * 根据成员IP地址查找联盟成员.
     * @param allianceIp ip地址
     * @return 联盟成员
     **/
    Alliance findByAllianceIp(String allianceIp);

    /**
     * 查询所有联盟成员.
     * @return 联盟成员集合
     **/
    List<Alliance> selectAllIp();
}
