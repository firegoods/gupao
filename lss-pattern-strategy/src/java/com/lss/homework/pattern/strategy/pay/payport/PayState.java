package com.lss.homework.pattern.strategy.pay.payport;

/**
 * 支付完成之后的状态
 * Created by liushaoshuai on 2018/3/19.
 */
public class PayState {
    private String code;
    private String msg;
    private double amount;


    public PayState (String uid, String msg, double amount){
        this.code = uid;
        this.msg = msg;
        this.amount = amount;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        return sb.append("支付状态： ").append(this.code)
                .append(",").append(msg)
                .append(",详情：").append(amount).toString();
    }
}
