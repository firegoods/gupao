package com.lss.spring.framework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class LssAopProxyUtils {
    public static Object getTargetObject(Object proxy) throws Exception{
        //先判断传进来的对象是不是一个代理过的对象
        //如果不是一个代理对象，直接返回
        if (!isAopProxy(proxy)){
            return proxy;
        }
        return getProxyTargetObject(proxy);

    }
    private  static boolean isAopProxy(Object proxy){
        return Proxy.isProxyClass(proxy.getClass());
    }

    private   static Object getProxyTargetObject(Object proxy) throws Exception{
        //这个proxy是jdk代理通过字节码重组生成的对象
        //Field h = proxy.getClass().getDeclaredField("h");
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);

        //这个才是真正的代理对象，就是实现InvocationHandler接口的那个类
        LssAopProxy aopProxy = (LssAopProxy)h.get(proxy);
        Field target =  aopProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);

        return target.get(aopProxy);


    }
}
