package com.lss.homework.pattern.strategy.pay.payport;

/**
 *
 * 这里使用枚举有个好处就是调用的用户不会自已创造支付类型
 * 而是在统一管理支付渠道
 * 这里使用注入式的单列
 * Created by liushaoshuai on 2018/3/19.
 */
public enum  PayType {
    ALI_PAY(new AliPay()),
    JD_PAY(new JDPay());


    private Payment payment;

    PayType (Payment payment){
        this.payment = payment;
    }
    public Payment getPayment(){
        return this.payment;
    }
}
