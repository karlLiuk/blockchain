package com.lk.coin.domain.bo;

import java.util.List;

/**
 * @Description 每个交易的实体信息.
 * @Date 2020/7/2
 * @Author lk
 */
public class DealInfo {
    /**
     * 交易发起方用户ID.
     **/
    private int fromUserId;

    /**
     * 交易发起方用于转账的CoinInfo列表.
     **/
    private List<CoinInfo> fromCoinList;

    /**
     * 交易接收方用户ID.
     **/
    private int toUserId;

    /**
     * 交易费用.
     **/
    private double dealCost;

    /**
     * 打包成区块的费用.
     **/
    private double blockCost;

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
     * 获取fromCoinList.
     *
     * @return fromCoinList
     **/
    public List<CoinInfo> getFromCoinList() {
        return fromCoinList;
    }

    /**
     * 设置fromCoinList.
     *
     * @Param the fromCoinList to set
     **/
    public void setFromCoinList(final List<CoinInfo> fromCoinList) {
        this.fromCoinList = fromCoinList;
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
     * 获取dealCost.
     *
     * @return dealCost
     **/
    public double getDealCost() {
        return dealCost;
    }

    /**
     * 设置dealCost.
     *
     * @Param the dealCost to set
     **/
    public void setDealCost(final double dealCost) {
        this.dealCost = dealCost;
    }

    /**
     * 获取blockCost.
     *
     * @return blockCost
     **/
    public double getBlockCost() {
        return blockCost;
    }

    /**
     * 设置blockCost.
     *
     * @Param the blockCost to set
     **/
    public void setBlockCost(final double blockCost) {
        this.blockCost = blockCost;
    }
}
