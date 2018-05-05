package com.lss.spring.framework.context;

import com.lss.spring.framework.beans.LssBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LssDefaultListableBeanFactory extends LssAbstractApplicationContext {

    protected Map<String,LssBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, LssBeanDefinition>();

    @Override
    protected void onRefresh() {
        super.onRefresh();
    }

    @Override
    protected void refreshBeanFactory() {

    }
}
