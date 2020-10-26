package com.lk.alliance.service;

/**
 * @Description 联盟成员操作Service接口.
 * @Date 2020/7/7
 * @Author lk
 */
public interface IAllianceService {

    /**
     * 插入数据.
     * @param allianceIp ip地址
     * @param allianceId id
     * @param allianceName 成员名称
     * @return void
     **/
    void insertIntoAlliance(String allianceIp, String allianceId, String allianceName);
}
