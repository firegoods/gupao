package com.lss.spring.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//默认使用jdk动态代理
//有接口的使用jdk
//没接口的使用cglib
//spring中用AopAutoProxyConfigurer封装aop信息如：pointcut、aspect
public class LssAopProxy implements InvocationHandler{

    private Object target;

    private LssAopConfig config;

    public  void  setConfig(LssAopConfig config){
        this.config = config;
    }


    //把原生的对象传入进来
    public Object getProxy(Object instance){
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = this.target.getClass().getMethod(method.getName(),method.getParameterTypes());
        //存在一个pointcut配置的方法要调用前执行的aop切面
        //在原始方法调用之前要执行的增强的代码
        //通过原生的方法去找，通过代理方法去Map中找是找不到的
        if (config.contains(m)){
            LssAopConfig.LssAspect aspect = config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }
        //obj是方法的返回值
        Object obj = method.invoke(this.target, args);

        //存在一个pointcut配置的方法要调用后执行的aop切面
        //在原始方法调用之后要执行的增强的代码
        if (config.contains(m)){
            LssAopConfig.LssAspect aspect = config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }
        //将最原始的返回值返回出去
        return obj;
    }
}
