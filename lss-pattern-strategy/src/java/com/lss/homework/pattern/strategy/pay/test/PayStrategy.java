package com.lss.homework.pattern.strategy.pay.test;

import com.lss.homework.pattern.strategy.pay.payport.Order;
import com.lss.homework.pattern.strategy.pay.payport.PayType;

/**
 * Created by liushaoshuai on 2018/3/19.
 */
public class PayStrategy {

    public static void main(String[] args) {
        //用户开始从商城中选择商品，加入购物车
        //将购物车的商品下订单
        Order order = new Order("1","201802190001",345.69);


        //开始选择支付方式：支付宝，京东白条，银联，微信
        //每种支付渠道的算法不一样，但是基本算法一致

         //每个订单的支付渠道只有在支付的时候才能知道具体是那个值
        System.out.println(order.pay(PayType.JD_PAY));
    }
}
