package com.lk.manage.impl.controller;

import com.google.common.base.Strings;
import com.lk.manage.controller.IUserToJoinController;
import com.lk.manage.service.IUserToJoinService;
import com.lk.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 成员申请Controller.
 * @Date 2020/7/7
 * @Author lk
 */
@RestController
@RequestMapping("/blockChain/manage/member")
public class UserToJoinController implements IUserToJoinController {

    /**
     * 日志对象.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserToJoinController.class);
    
    /**
     * 成员申请加入联盟Service.
     **/
    @Autowired
    private IUserToJoinService userToJoinService;

    /**
     * 申请加入联盟.
     * @param orgName 企业名称
     * @param orgPhone 联系方式
     * @param orgRepresent 联系人
     * @return java.lang.String
     **/
    @Override
    @RequestMapping("/join")
    public String join(final String orgName, final String orgPhone, final String orgRepresent) {
        if (Strings.isNullOrEmpty(orgName)
            || Strings.isNullOrEmpty(orgPhone)
            || Strings.isNullOrEmpty(orgRepresent)) {
            return "请输入企业名称、联系方式和联系人";
        }

        if (CommonUtil.isMobileOrPhone(orgPhone)) {
            return "联系人手机号码格式不正确";
        }
        userToJoinService.join(orgName, orgPhone, orgRepresent);
        return "success";
    }
}
