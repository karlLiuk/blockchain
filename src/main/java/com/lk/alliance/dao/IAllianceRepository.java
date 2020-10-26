package com.lk.alliance.dao;

import com.lk.alliance.domain.model.Alliance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 联盟成员Repository.
 * @Date 2020/7/7
 * @Author lk
 */
public interface IAllianceRepository extends JpaRepository<Alliance, Long> {

    /**
     * 根据成员IP地址查找联盟成员.
     * @param allianceIp ip地址
     * @return Alliance 联盟成员
     **/
    Alliance findByAllianceIp(String allianceIp);
}
