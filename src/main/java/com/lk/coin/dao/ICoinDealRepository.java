package com.lk.coin.dao;

import com.lk.coin.domain.model.CoinDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 联盟内币的交易明细表Dao层.
 * @Date 2020/7/2
 * @Author lk
 */
@Repository
public interface ICoinDealRepository extends JpaRepository<CoinDeal, Long> {

}
