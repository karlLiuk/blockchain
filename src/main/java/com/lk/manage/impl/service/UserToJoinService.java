package com.lk.manage.impl.service;

import com.lk.manage.dao.IUserToJoinRepository;
import com.lk.manage.domain.UserToJoin;
import com.lk.manage.service.IUserToJoinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 成员申请Service.
 * @Date 2020/7/7
 * @Author lk
 */
@Service
@Transactional
public class UserToJoinService implements IUserToJoinService {

    /**
     * 成员申请信息Repository.
     **/
    private IUserToJoinRepository userToJoinRepository;

    /**
     * 申请加入联盟.
     * @param orgName 企业名称
     * @param orgPhone 联系方式
     * @param orgRepresent 联系人
     * @return java.lang.String
     **/
    @Override
    public void join(final String orgName, final String orgPhone, final String orgRepresent) {
        final UserToJoin user =
                userToJoinRepository.getByOrgNameAndOrgPhoneAndOrgRepresent(orgName, orgPhone, orgRepresent);
        if (user != null) {
            return;
        }

        final UserToJoin userToJoin = new UserToJoin();
        userToJoin.setOrgName(orgName);
        userToJoin.setOrgPhone(orgPhone);
        userToJoin.setOrgRepresent(orgRepresent);
        userToJoin.setResult(0);

        userToJoinRepository.save(userToJoin);
    }
}
