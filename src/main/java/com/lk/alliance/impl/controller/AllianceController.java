package com.lk.alliance.impl.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.lk.alliance.controller.IAllianceController;
import com.lk.alliance.service.IAllianceService;
import com.lk.alliance.service.IKeySuitService;
import com.lk.util.CommonUtil;
import com.lk.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 联盟成员操作Controller.
 * @Date 2020/7/8
 * @Author lk
 */
@RestController
@RequestMapping("/blockChain/alliance/service")
public class AllianceController implements IAllianceController {

    /**
     * 联盟成员操作Service.
     **/
    @Autowired
    private IAllianceService allianceService;

    private IKeySuitService keySuitService;

    /**
     * 插入数据.
     * @param allianceIp ip地址
     * @param allianceId id
     * @param allianceName 成员名称
     * @return void
     **/
    @Override
    @GetMapping("/insert")
    public String insertIntoAlliance(final String allianceIp, final String allianceId, final String allianceName) {
        if (Strings.isNullOrEmpty(allianceId)
            || Strings.isNullOrEmpty(allianceIp)
            || Strings.isNullOrEmpty(allianceName)) {
            return "输入有空，请输入完整";
        }

        if (!IpUtil.isIp(allianceIp)) {
            return "IP格式不对";
        }

        allianceService.insertIntoAlliance(allianceIp, allianceId, allianceName);
        return JSON.toJSONString(keySuitService.getRandowKeySuit());
    }
}
