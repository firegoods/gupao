package com.lss.spring.framework.beans;

import com.lss.spring.framework.core.FactoryBean;

public class BeanWrapper extends FactoryBean {

    //还会用到观察者模式
    //1、支持事件响应，会有一个事件监听
    private BeanPostProcessor beanPostProcessor;

    private Object wrapperInstance;

    //原始的对象通过反射new出来之后，要包装起来，存下来
    private Object originalInstance;

    public BeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.originalInstance = instance;
    }

    public BeanPostProcessor getBeanPostProcessor() {
        return beanPostProcessor;
    }

    public void setBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessor = beanPostProcessor;
    }

    public Object getWrapperInstance() {
        return this.wrapperInstance;
    }

    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }
}
