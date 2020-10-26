package com.lk.alliance.impl.controller;

import com.google.common.base.Strings;
import com.lk.alliance.controller.IAllianceQueryController;
import com.lk.alliance.domain.model.Alliance;
import com.lk.alliance.service.IAllianceQueryService;
import com.lk.util.CommonUtil;
import com.lk.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 联盟成员查询Controller.
 * @Date 2020/7/8
 * @Author lk
 */
public class AllianceQueryController implements IAllianceQueryController {

    /**
     * 联盟成员查询Service.
     */
    @Autowired
    private IAllianceQueryService allianceQueryService;

    /**
     * 根据成员IP地址查找联盟成员.
     *
     * @param allianceIp ip地址
     * @return 联盟成员
     **/
    @Override
    @GetMapping("/findByIp")
    public Alliance findByAllianceIp(final String allianceIp, final HttpServletRequest request) {
        if (Strings.isNullOrEmpty(allianceIp)) {
            return null;
        }

        if (!IpUtil.isIp(allianceIp)) {
            return null;
        }

        // 输入的IP与请求的IP来源不一致，非法请求
        if (!IpUtil.getIpAddr(request).equals(allianceIp)) {
            return null;
        }
        return allianceQueryService.findByAllianceIp(allianceIp);
    }

    /**
     * 查询所有联盟成员.
     *
     * @return 联盟成员集合
     **/
    @Override
    public List<Alliance> selectAllIp(final String allianceIp, final HttpServletRequest request) {
        if (Strings.isNullOrEmpty(allianceIp)) {
            return null;
        }

        if (!IpUtil.isIp(allianceIp)) {
            return null;
        }

        // 输入的IP与请求的IP来源不一致，非法请求
        if (!IpUtil.getIpAddr(request).equals(allianceIp)) {
            return null;
        }
        return allianceQueryService.selectAllIp();
    }
}
