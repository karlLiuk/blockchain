package com.lk.coin.domain.bo;

/**
 * @Description 每个币的实体信息.
 * @Date 2020/7/2
 * @Author lk
 */
public class CoinInfo {
    /**
     * 币地址.
     **/
    private String address;

    /**
     * 币地址对应的余额.
     **/
    private double balance;

    /**
     * 获取address.
     *
     * @return address
     **/
    public String getAddress() {
        return address;
    }

    /**
     * 设置address.
     *
     * @Param the address to set
     **/
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 获取balance.
     *
     * @return balance
     **/
    public double getBalance() {
        return balance;
    }

    /**
     * 设置balance.
     *
     * @Param the balance to set
     **/
    public void setBalance(final double balance) {
        this.balance = balance;
    }
}
