package com.lk.manage.dao;

import com.lk.manage.domain.UserToJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 成员申请信息Repository.
 * @Date 2020/7/2
 * @Author lk
 */
@Repository
public interface IUserToJoinRepository extends JpaRepository<UserToJoin, Long> {

    /**
     * 根据机构名称、联系方式和联系人查询成员申请信息.
     * @param orgName 机构名称
     * @param orgPhone 联系方式
     * @param orgRepresent 联系人
     * @return 成员申请信息
     **/
    UserToJoin getByOrgNameAndOrgPhoneAndOrgRepresent(String orgName, String orgPhone, String orgRepresent);

    /**
     * 根据申请结果查询成员申请信息.
     * @param result 申请结果
     * @return 成员申请信息集合.
     **/
    List<UserToJoin> getByResult(int result);
}
