package com.lss.pattern.observer.subject;

import com.lss.pattern.observer.core.EventListener;

/**
 * Created by liushaoshuai on 2018/3/26.
 */
public class Subject extends EventListener {

    //通常动态来实现监控，避免代码的入侵
    public void add(){
        System.out.println("调用添加的方法！");
        trigger(SubjectEventType.ON_ADD);
    }

    public void remove(){
        System.out.println("调用删除的方法！");
        trigger(SubjectEventType.ON_REMOVE);
    }
}
