package com.lss.homework.pattern.strategy.pay.payport;

/**
 * 支付渠道接口
 * Created by liushaoshuai on 2018/3/19.
 */
public interface Payment {

    public PayState pay(String uid, double amount);

}
