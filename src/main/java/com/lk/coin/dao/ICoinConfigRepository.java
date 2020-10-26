package com.lk.coin.dao;

import com.lk.coin.domain.model.CoinConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 联盟内激励配置表Dao层.
 * @Date 2020/7/2
 * @Author lk
 */
@Repository
public interface ICoinConfigRepository extends JpaRepository<CoinConfig, Long> {

    /**
     * 根据业务类型查询激励配置.
     * @param bizType 业务内容
     * @return 联盟内激励配置
     **/
    CoinConfig findByBizType(String bizType);
}
