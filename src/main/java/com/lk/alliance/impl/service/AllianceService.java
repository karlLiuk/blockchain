package com.lk.alliance.impl.service;

import com.lk.alliance.dao.IAllianceRepository;
import com.lk.alliance.domain.model.Alliance;
import com.lk.alliance.service.IAllianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description 联盟成员操作Service.
 * @Date 2020/7/7
 * @Author lk
 */
@Service
@Transactional
public class AllianceService implements IAllianceService {

    /**
     * 数据访问.
     **/
    @Autowired
    private IAllianceRepository allianceRepository;

    /**
     * 插入数据.
     * @param allianceIp ip地址
     * @param allianceId id
     * @param allianceName 成员名称
     * @return void
     **/
    @Override
    public void insertIntoAlliance(final String allianceIp, final String allianceId, final String allianceName) {
        final Alliance alliance = new Alliance();
        alliance.setAllianceId(allianceId);
        alliance.setAllianceIp(allianceIp);
        alliance.setAllianceName(allianceName);
        alliance.setCreateTime(new Date());
        alliance.setUpdateTime(new Date());

        allianceRepository.save(alliance);
    }
}
