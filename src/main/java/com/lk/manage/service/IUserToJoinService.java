package com.lk.manage.service;

/**
 * @Description 成员申请Service接口.
 * @Date 2020/7/7
 * @Author lk
 */
public interface IUserToJoinService {
    /**
     * 申请加入联盟.
     * @param orgName 企业名称
     * @param orgPhone 联系方式
     * @param orgRepresent 联系人
     * @return java.lang.String
     **/
    void join(String orgName, String orgPhone, String orgRepresent);
}
