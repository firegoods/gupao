package com.lss.java.tutorial.basic;

/**
 * Created by liushaoshuai on 2018/4/2.
 */
public interface Demo {


    public default void test(){
        System.out.println("public defalut");
    }
}
