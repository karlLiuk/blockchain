package com.lk.coin.dao;

import com.lk.coin.domain.model.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 联盟内用户钱包表Dao层.
 * @Date 2020/7/2
 * @Author lk
 */
@Repository
public interface IUserWalletRepository extends JpaRepository<UserWallet, Long> {

}
