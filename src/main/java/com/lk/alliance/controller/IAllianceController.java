package com.lk.alliance.controller;

/**
 * @Description 联盟成员操作Controller接口.
 * @Date 2020/7/8
 * @Author lk
 */
public interface IAllianceController {
    /**
     * 插入数据.
     * @param allianceIp ip地址
     * @param allianceId id
     * @param allianceName 成员名称
     * @return void
     **/
    String insertIntoAlliance(String allianceIp, String allianceId, String allianceName);
}
