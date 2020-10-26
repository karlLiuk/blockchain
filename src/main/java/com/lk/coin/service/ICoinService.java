package com.lk.coin.service;

import com.lk.coin.domain.bo.CoinInfo;
import com.lk.coin.domain.bo.DealInfo;

import java.util.List;

/**
 * @Description 联盟链中币交易服务层接口.
 * @Date 2020/7/2
 * @Author lk
 */
public interface ICoinService {

    /**
     * 模拟区块交易.
     * @param dealInfo 交易信息
     * @param allianceNodeId 联盟节点信息
     * @return void
     **/
    void deal(DealInfo dealInfo, int allianceNodeId);

    /**
     * 保存该条币信息到收款人，同时保存到交易流水记录.
     * @param fromAddress 交易发起方币地址
     * @param toAddress 交易接收方币地址
     * @param coinBalance 币地址对应的余额
     * @param coinBalanceDealed 交易后账户余额.
     * @param toUserId 交易接收方ID
     * @param fromUserId 交易发起方ID
     * @return void
     **/
    void save(final String fromAddress, final String toAddress, final double coinBalance,
                      final double coinBalanceDealed,
                      final int toUserId, final int fromUserId);

    /**
     * 给区块生成的节点增加费用收入记录.
     **/
    void insertBlockCostToAlliance();

    /**
     * 处理交易.
     * 此处略去将数据JSON化写入区块的部分
     * @param dealInfo 交易信息
     * @param allianceNodeId 联盟节点信息
     * @return void
     **/
    void processDeal(final DealInfo dealInfo, final int allianceNodeId);

    /**
     * 将区块生成的奖励入库.
     * @param allianceNodeId 联盟节点信息
     * @return void
     **/
    void createBlockToAlliance(int allianceNodeId);

    /**
     * 交易信息内容校验.
     * @param dealInfo 交易信息
     * @return boolean 校验结果
     **/
    boolean checkDealInfo(final DealInfo dealInfo);

    /**
     * 计算交易列表中涉及的交易地址对应的余额总量.
     * @param fromCoinList 交易发起方的交易列表
     * @return double 交余额总量
     **/
    double getAllCoinBalances(final List<CoinInfo> fromCoinList);
}
