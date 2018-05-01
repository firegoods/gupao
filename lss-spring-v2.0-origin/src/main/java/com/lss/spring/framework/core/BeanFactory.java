package com.lss.spring.framework.core;

public interface BeanFactory {

    /**
     * 根据beanName从IOC容器中获取一个bean实例
     * @param beanName
     * @return
     */
    Object getBean(String beanName);


}
