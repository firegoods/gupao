package com.lss.homework.pattern.strategy.pay.payport;

/**
 *
 * 订单
 * Created by liushaoshuai on 2018/3/19.
 */
public class Order {
    private String oderId;
    private String code;
    private double amount;

    public Order(String orderId, String code, double amount){
        this.oderId = orderId;
        this.code = code;
        this.amount = amount;
    }

    //这个可以传一个payment 然后通过if else 判断具体调用那种支付，但是我们没有用？为什么？

    //这个添加一个支付类型，完美的解决了switch问题，用户在前台选在对应的支付渠道即可
    //同时还把支付和订单解耦，如果用switch来控制，会导致每新增一种支付就要修改if else，不符合开闭原则
    public PayState pay(PayType payType){
        return payType.getPayment().pay(this.code,this.amount);
    }

}
