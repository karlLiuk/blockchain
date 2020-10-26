package com.lk.coin.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 交易明细表.
 * @Date 2020/7/2
 * @Author lk
 */
@Entity
@Table(name = "tb_coin_deal")
public class CoinDeal {
    /**
     * 主键.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 交易发起方用户ID.
     **/
    private int fromUserId;

    /**
     * 交易发起方地址.
     **/
    private String fromAddress;

    /**
     * 交易前账户余额.
     **/
    private double coinBalance;

    /**
     * 交易接收方用户ID.
     **/
    private int toUserId;

    /**
     * 交易接收方地址.
     **/
    private String toAddress;

    /**
     * 交易后账户余额.
     **/
    private double coinBalanceDealed;

    /**
     * 交易创建时间.
     **/
    private Date createTime;

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
     * 获取fromUserId.
     *
     * @return fromUserId
     **/
    public int getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置fromUserId.
     *
     * @Param the fromUserId to set
     **/
    public void setFromUserId(final int fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取fromAddress.
     *
     * @return fromAddress
     **/
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * 设置fromAddress.
     *
     * @Param the fromAddress to set
     **/
    public void setFromAddress(final String fromAddress) {
        this.fromAddress = fromAddress;
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
     * 获取toUserId.
     *
     * @return toUserId
     **/
    public int getToUserId() {
        return toUserId;
    }

    /**
     * 设置toUserId.
     *
     * @Param the toUserId to set
     **/
    public void setToUserId(final int toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取toAddress.
     *
     * @return toAddress
     **/
    public String getToAddress() {
        return toAddress;
    }

    /**
     * 设置toAddress.
     *
     * @Param the toAddress to set
     **/
    public void setToAddress(final String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * 获取coinBalanceDealed.
     *
     * @return coinBalanceDealed
     **/
    public double getCoinBalanceDealed() {
        return coinBalanceDealed;
    }

    /**
     * 设置coinBalanceDealed.
     *
     * @Param the coinBalanceDealed to set
     **/
    public void setCoinBalanceDealed(final double coinBalanceDealed) {
        this.coinBalanceDealed = coinBalanceDealed;
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
}
