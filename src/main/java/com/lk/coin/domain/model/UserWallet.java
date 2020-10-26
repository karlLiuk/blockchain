package com.lk.coin.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 联盟内用户钱包，用于记录钱包内的币地址和余额.
 * @Date 2020/7/2
 * @Author lk
 */
@Entity
@Table(name = "tb_user_wallet")
public class UserWallet {

    /**
     * 主键.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 联盟节点ID.
     **/
    private int userId;

    /**
     * 币地址.
     **/
    private String coinAddress;

    /**
     * 币地址对应的余额.
     **/
    private double coinBalance;

    /**
     * 交易创建时间.
     **/
    private Date createTime;

    /**
     * 交易更新时间.
     **/
    private Date updateTime;

    /**
     * 获取id.
     *
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * 设置id.
     *
     * @Param the id to set
     **/
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 获取userId.
     *
     * @return userId
     **/
    public int getUserId() {
        return userId;
    }

    /**
     * 设置userId.
     *
     * @Param the userId to set
     **/
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    /**
     * 获取coinAddress.
     *
     * @return coinAddress
     **/
    public String getCoinAddress() {
        return coinAddress;
    }

    /**
     * 设置coinAddress.
     *
     * @Param the coinAddress to set
     **/
    public void setCoinAddress(final String coinAddress) {
        this.coinAddress = coinAddress;
    }

    /**
     * 获取coinBalance.
     *
     * @return coinBalance
     **/
    public double getCoinBalance() {
        return coinBalance;
    }

    /**
     * 设置coinBalance.
     *
     * @Param the coinBalance to set
     **/
    public void setCoinBalance(final double coinBalance) {
        this.coinBalance = coinBalance;
    }

    /**
     * 获取createTime.
     *
     * @return createTime
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime.
     *
     * @Param the createTime to set
     **/
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime.
     *
     * @return updateTime
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置updateTime.
     *
     * @Param the updateTime to set
     **/
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
}
