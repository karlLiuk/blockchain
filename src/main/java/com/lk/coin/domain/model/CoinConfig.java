package com.lk.coin.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 联盟内激励配置表.
 * @Date 2020/7/2
 * @Author lk
 */
@Entity
@Table(name = "tb_coin_config")
public class CoinConfig {

    /**
     * 主键.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 业务类型.
     **/
    private String bizType;

    /**
     * 币的总量.
     **/
    private int coinTotal;

    /**
     * 机构预留币的数量.
     **/
    private int coinReserved;

    /**
     * 每笔交易最小的币支付金额.
     **/
    private double coinPerDeal;

    /**
     * 生成一个区块的奖励币数量.
     **/
    private double coinBlockCreate;

    /**
     * 规则配置时间.
     **/
    private Date createTime;

    /**
     * 规则修改时间.
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
     * 获取bizType.
     *
     * @return bizType
     **/
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置bizType.
     *
     * @Param the bizType to set
     **/
    public void setBizType(final String bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取coinTotal.
     *
     * @return coinTotal
     **/
    public int getCoinTotal() {
        return coinTotal;
    }

    /**
     * 设置coinTotal.
     *
     * @Param the coinTotal to set
     **/
    public void setCoinTotal(final int coinTotal) {
        this.coinTotal = coinTotal;
    }

    /**
     * 获取coinReserved.
     *
     * @return coinReserved
     **/
    public int getCoinReserved() {
        return coinReserved;
    }

    /**
     * 设置coinReserved.
     *
     * @Param the coinReserved to set
     **/
    public void setCoinReserved(final int coinReserved) {
        this.coinReserved = coinReserved;
    }

    /**
     * 获取coinPerDeal.
     *
     * @return coinPerDeal
     **/
    public double getCoinPerDeal() {
        return coinPerDeal;
    }

    /**
     * 设置coinPerDeal.
     *
     * @Param the coinPerDeal to set
     **/
    public void setCoinPerDeal(final double coinPerDeal) {
        this.coinPerDeal = coinPerDeal;
    }

    /**
     * 获取coinBlockCreate.
     *
     * @return coinBlockCreate
     **/
    public double getCoinBlockCreate() {
        return coinBlockCreate;
    }

    /**
     * 设置coinBlockCreate.
     *
     * @Param the coinBlockCreate to set
     **/
    public void setCoinBlockCreate(final double coinBlockCreate) {
        this.coinBlockCreate = coinBlockCreate;
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
