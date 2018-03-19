package com.lss.homework.pattern.strategy.pay.payport;

/**
 * Created by liushaoshuai on 2018/3/19.
 */
public class AliPay implements  Payment{


    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("欢迎使用支付宝！");
        System.out.println("正在查询余额,开始扣款！");
        return new PayState(uid,"支付成功",amount);
    }
}
