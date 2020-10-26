package com.lk.alliance.impl.service;

import com.lk.alliance.dao.IAllianceRepository;
import com.lk.alliance.domain.model.Alliance;
import com.lk.alliance.service.IAllianceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 联盟成员查询Service.
 * @Date 2020/7/7
 * @Author lk
 */
@Service
@Transactional
public class AllianceQueryService implements IAllianceQueryService {

    /**
     * 数据访问.
     **/
    @Autowired
    private IAllianceRepository allianceRepository;

    /**
     * 根据成员IP地址查找联盟成员.
     * @param allianceIp ip地址
     * @return 联盟成员
     **/
    @Override
    public Alliance findByAllianceIp(final String allianceIp) {
        return allianceRepository.findByAllianceIp(allianceIp);
    }

    /**
     * 查询所有联盟成员.
     * @return 联盟成员集合
     **/
    @Override
    public List<Alliance> selectAllIp() {
        return allianceRepository.findAll();
    }
}
