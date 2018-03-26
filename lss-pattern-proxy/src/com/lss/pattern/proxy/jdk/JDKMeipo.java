package com.lss.pattern.proxy.jdk;

import com.lss.pattern.proxy.statied.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liushaoshuai on 2018/3/26.
 */
public class JDKMeipo implements InvocationHandler {


    private Person target;

    public Object getInstance(Person target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆开始帮你物色对象：");
        System.out.println("请讲一下你的要求：");
        method.invoke(this.target,args);
        System.out.println("正在找对象。。。。。");

        return null;
    }
}
