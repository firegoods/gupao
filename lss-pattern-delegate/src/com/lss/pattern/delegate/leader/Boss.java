package com.lss.pattern.delegate.leader;

/**
 * Created by liushaoshuai on 2018/3/21.
 */
public class Boss {

    public static void main(String[] args) {

        //客户请求 boss, 委派者 leader ， 被委派者 employee
        //委派者要拿到被委派者的引用
        //代理注重的是过程，委派注重的是结果
        //策略模式注重的是外部的扩展，委派注重的是内部的灵活复用
        //委派的核心是：分发，调度，派遣

        new Leader().doSomethings("登录");
    }
}
