package com.lss.pattern.observer.subject;

import com.lss.pattern.observer.core.Event;

import java.lang.reflect.Method;

/**
 * Created by liushaoshuai on 2018/3/26.
 */
public class SubjectEventTest {

    public static void main(String[] args) {
        try {
            //观察者
            Observer observer = new Observer();
            Method advice =  Observer.class.getMethod("advice", new Class[]{Event.class});

            //这里写被观察者
            //配置观察者和被观察者的联系
            Subject subject = new Subject();
            subject.addListener(SubjectEventType.ON_ADD,observer,advice);
            subject.addListener(SubjectEventType.ON_REMOVE,observer,advice);
            subject.add();
            subject.remove();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
