package com.lss.spring.demo.aspect;

public class LogAspect {

    //在调用一个方法之前，执行before方法
    public void before(){
        System.out.println("invoke method before!");

    }

    //在调用一个方法之后，执行after方法
    public void after(){
        System.out.println("invoke method after!");
    }
}
