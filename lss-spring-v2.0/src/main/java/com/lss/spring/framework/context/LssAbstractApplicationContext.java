package com.lss.spring.framework.context;

public abstract class LssAbstractApplicationContext {

    protected void onRefresh()  {
        // For subclasses: do nothing by default.
    }

    protected abstract void refreshBeanFactory();
}
