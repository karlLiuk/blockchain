package com.lk.coin.impl.service;

import com.lk.coin.dao.ICoinDealRepository;
import com.lk.coin.dao.IUserWalletRepository;
import com.lk.coin.domain.bo.CoinInfo;
import com.lk.coin.domain.bo.DealInfo;
import com.lk.coin.domain.model.CoinDeal;
import com.lk.coin.domain.model.UserWallet;
import com.lk.coin.service.ICoinService;
import com.lk.util.CollectionUtil;
import com.xiaoleilu.hutool.crypto.digest.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 联盟链中币交易服务层.
 * @Date 2020/7/2
 * @Author lk
 */
public class CoinService implements ICoinService {

    /**
     * 日志对象.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(CoinService.class);

    /**
     * 用户钱包表Dao层.
     **/
    @Autowired
    private IUserWalletRepository userWalletRepository;

    /**
     * 交易明细表Dao层.
     **/
    @Autowired
    private ICoinDealRepository coinDealRepository;

    /**
     * 节点新生成区块的奖励币数量.
     **/
    private static final int CREATE_BLOCK_COST = 100;

    /**
     * 模拟区块交易.
     * @param dealInfo 交易信息
     * @param allianceNodeId 联盟节点信息
     * @return void
     **/
    @Override
    public void deal(final DealInfo dealInfo, final int allianceNodeId) {
        // allianceNodeId有效性校验
        if (allianceNodeId <= 0) {
            return;
        }

        if (dealInfo == null) {
            return;
        }

        // 校验结果不通过
        if (!checkDealInfo(dealInfo)) {
            return;
        }

        // 处理交易
        processDeal(dealInfo, allianceNodeId);
    }

    /**
     * 给区块生成的节点增加费用收入记录.
     **/
    @Override
    public void insertBlockCostToAlliance() {
        final UserWallet userWallet = new UserWallet();
//        userWallet.setCoinAddress(coinAddress);
        userWalletRepository.save(userWallet);
    }

    /**
     * 处理交易.
     * 此处略去将数据JSON化写入区块的部分
     * @param dealInfo 交易信息
     * @param allianceNodeId 联盟节点信息
     * @return void
     **/
    @Override
    public void processDeal(final DealInfo dealInfo, final int allianceNodeId) {
        // 处理并保存交易信息
        processAndSaveDeal(dealInfo);

        // 将区块生成的奖励入库
        createBlockToAlliance(allianceNodeId);
    }

    /**
     * 将区块生成的奖励入库.
     * @param allianceNodeId 联盟节点信息
     * @return void
     **/
    @Override
    public void createBlockToAlliance(final int allianceNodeId) {
        final UserWallet userWallet = new UserWallet();

        // 新生成一个币地址用于存储区块打包费用
        final String coinAddress = DigestUtil.sha256Hex("创建区块，时间是："
                + System.currentTimeMillis());
        userWallet.setCoinAddress(coinAddress);

        // 设置新生成区块的奖励
        double coinBalance = CREATE_BLOCK_COST;
        userWallet.setCoinBalance(coinBalance);

        // 设计奖励所属人
        userWallet.setUserId(allianceNodeId);

        userWallet.setCreateTime(new Date());
        userWallet.setUpdateTime(new Date());

        userWalletRepository.save(userWallet);
    }

    /**
     * 处理并保存交易信息.
     * @param dealInfo 交易信息
     * @return void
     **/
    private void processAndSaveDeal(final DealInfo dealInfo) {
        final int fromUserId = dealInfo.getFromUserId();
        final int toUserId = dealInfo.getToUserId();
        double blockCost = dealInfo.getBlockCost();
        final List<CoinInfo> fromCoinList = dealInfo.getFromCoinList();
        final double dealCost = dealInfo.getDealCost();

        // 按balance从小到大排序
        final List<CoinInfo> sortedCoinList = fromCoinList.stream()
                .sorted(Comparator.comparing(CoinInfo::getBalance)).collect(Collectors.toList());

        // 查找满足交易费用的1个或多个address及对应的余额balance
        int index = 0;
        final int size = sortedCoinList.size();
        double sumBalance = 0;
        for (int i = 0; i < size; i++) {
            final CoinInfo coinInfo = sortedCoinList.get(i);
            sumBalance += coinInfo.getBalance();

            // 保存该条币信息到收款人，同时保存到交易流水记录
            save(coinInfo.getAddress(), coinInfo.getAddress(),
                    coinInfo.getBalance(), coinInfo.getBalance(),toUserId, fromUserId);

            if (sumBalance > dealCost) {
                index = i;

                // 分析该条数据为哪两个账户
                final double toUserDiffBalance = dealCost - (sumBalance - coinInfo.getBalance());
                // 生成一个新币地址
                final String coinAddress = DigestUtil.sha256Hex("创建区块，时间是："
                        + System.currentTimeMillis());

                save(null, coinAddress, toUserDiffBalance, toUserDiffBalance, toUserId, fromUserId);

                // 交易发起方账户变化，同时保存到交易流水记录
                save(coinInfo.getAddress(), coinInfo.getAddress(), coinInfo.getBalance(),
                        coinInfo.getBalance() - toUserDiffBalance, toUserId, fromUserId);
            }
        }

        // 处理交易打包成区块费用blockCost
        for (int i = index; i < size; i++) {
            final CoinInfo coinInfo2 = sortedCoinList.get(i);
            if (coinInfo2.getBalance() <= blockCost) {
                // 直接转移币地址即可

                // 保存记录到收款人并保存交易流水记录
                save(coinInfo2.getAddress(), coinInfo2.getAddress(), coinInfo2.getBalance(),
                        coinInfo2.getBalance(), toUserId, fromUserId);

                blockCost -= coinInfo2.getBalance();

                if (blockCost == 0.0) {
                    return;
                }
            } else {
                // 拆分成两个新账户

                // 生成一个新币地址
                final String coinAddress = DigestUtil.sha256Hex("创建区块，时间是："
                        + System.currentTimeMillis());
                save(null, coinAddress, blockCost, blockCost, toUserId, fromUserId);

                // 交易发起方账户变化，同时保存到交易流水记录
                save(coinInfo2.getAddress(), coinInfo2.getAddress(), coinInfo2.getBalance(),
                        coinInfo2.getBalance() - blockCost, toUserId, fromUserId);
            }
        }
    }

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
    @Override
    public void save(final String fromAddress, final String toAddress, final double coinBalance,
                      final double coinBalanceDealed,
                      final int toUserId, final int fromUserId) {
        // 保存用户钱包，用于记录钱包内的币地址和余额.
        final UserWallet userWallet = new UserWallet();
        userWallet.setCoinAddress(toAddress);
        userWallet.setCoinBalance(coinBalance);
        userWallet.setCreateTime(new Date());
        userWallet.setUpdateTime(new Date());
        userWallet.setUserId(toUserId);
        userWalletRepository.save(userWallet);

        // 保存交易明细
        final CoinDeal coinDeal = new CoinDeal();
        coinDeal.setCoinBalance(coinBalance);
        coinDeal.setCoinBalanceDealed(coinBalanceDealed);
        coinDeal.setCreateTime(new Date());
        coinDeal.setFromAddress(fromAddress);
        coinDeal.setFromUserId(fromUserId);
        coinDeal.setToUserId(toUserId);
        coinDeal.setToAddress(toAddress);
        coinDealRepository.save(coinDeal);
    }

    /**
     * 交易信息内容校验.
     * @param dealInfo 交易信息
     * @return boolean 校验结果
     **/
    @Override
    public boolean checkDealInfo(final DealInfo dealInfo) {

        // 交易双方Id必须大于0
        if (dealInfo.getFromUserId() <= 0 || dealInfo.getToUserId() < 0) {
            return false;
        }

        // 生成区块的交易费必须大于0
        if (dealInfo.getBlockCost() <= 0) {
            return false;
        }

        final List<CoinInfo> fromCoinList = dealInfo.getFromCoinList();
        // 交易发起方的交易列表不能为空
        if (CollectionUtil.isEmpty(fromCoinList)) {
            return false;
        }

        // 获取fromCoinList中涉及的交易地址对应的余额总量
        final double allBalances = getAllCoinBalances(fromCoinList);
        // 获取交易费用
        final double dealCost = dealInfo.getDealCost();
        // 余额不足交易费用或等于交易费用但没有用于支付区块创建的费用
        if (allBalances <= dealCost) {
            return false;
        }

        // 余额不足支付交易费用和创建区块的费用
        if (allBalances < dealCost + dealInfo.getBlockCost()) {

        }

        return true;
    }

    /**
     * 计算交易列表中涉及的交易地址对应的余额总量.
     * @param fromCoinList 交易发起方的交易列表
     * @return double 交余额总量
     **/
    @Override
    public double getAllCoinBalances(final List<CoinInfo> fromCoinList) {
        return fromCoinList.stream().mapToDouble(CoinInfo::getBalance).sum();
    }
}
