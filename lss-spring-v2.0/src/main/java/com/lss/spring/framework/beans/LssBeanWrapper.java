package com.lss.spring.framework.beans;

import com.lss.spring.framework.aop.LssAopConfig;
import com.lss.spring.framework.aop.LssAopProxy;
import com.lss.spring.framework.core.LssFactoryBean;

public class LssBeanWrapper extends LssFactoryBean {

    private LssAopProxy aopProxy= new LssAopProxy();

    //还会用到观察者模式
    //1、支持事件响应，会有一个事件监听
    private LssBeanPostProcessor lssBeanPostProcessor;

    private Object wrapperInstance;

    //原始的对象通过反射new出来之后，要包装起来，存下来
    private Object originalInstance;

    public LssBeanWrapper(Object instance) {

        //重这里开始，我们要把动态代理的代码加入进来了

        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }

    public LssBeanPostProcessor getLssBeanPostProcessor() {
        return lssBeanPostProcessor;
    }

    public void setLssBeanPostProcessor(LssBeanPostProcessor lssBeanPostProcessor) {
        this.lssBeanPostProcessor = lssBeanPostProcessor;
    }

    public Object getWrapperInstance() {
        return this.wrapperInstance;
    }

    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }

    public void setAopConfig(LssAopConfig config){
        aopProxy.setConfig(config);

    }

    public Object getOriginalInstance() {
        return originalInstance;
    }
}
