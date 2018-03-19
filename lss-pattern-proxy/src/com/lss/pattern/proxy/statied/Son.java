package com.lss.pattern.proxy.statied;

/**
 * Created by liushaoshuai on 2018/3/19.
 */
public class Son implements Person {

    @Override
    public void findLove() {
        //没时间找对象
        //工作忙
        System.out.println("找对象，肤白貌美大长腿！");
    }
}
