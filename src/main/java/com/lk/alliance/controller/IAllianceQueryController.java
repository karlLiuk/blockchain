package com.lk.alliance.controller;

import com.lk.alliance.domain.model.Alliance;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 联盟成员查询Controller接口.
 * @Date 2020/7/8
 * @Author lk
 */
public interface IAllianceQueryController {
    /**
     * 根据成员IP地址查找联盟成员.
     *
     * @param allianceIp ip地址
     * @return 联盟成员
     **/
    Alliance findByAllianceIp(String allianceIp, HttpServletRequest request);

    /**
     * 查询所有联盟成员.
     *
     * @return 联盟成员集合
     **/
    List<Alliance> selectAllIp(String allianceIp, HttpServletRequest request);
}
