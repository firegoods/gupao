package com.lss.pattern.observer.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 事件监听注册和监听
 * Created by liushaoshuai on 2018/3/23.
 */
public class EventListener {

    //map 相当于一个注册器
    protected Map<Enum,Event> events = new HashMap<Enum, Event>();

    public void addListener(Enum enumType,Object target,Method handle){
        //注册事件
        //用反射来调用这个方法
        events.put(enumType,new Event(target,handle));
    }

    private void trigger(Event e){
        //为什么事件源要设置自身
        /**
         * 事件触发打印事件源
         */
        e.setSource(this);
        e.setTime(System.currentTimeMillis());

        try {
            //为什么调用里面传event做参数
            /**
             * e.getCallback() 是MouseEventCallback的Methd
             * e.getTarget()是是MouseEventCallback的Methd对象
             * e.getCallback() 是MouseEventCallback的Methd的方法的参数
             *
             */
            e.getCallback().invoke(e.getTarget(), e);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }

    }

    protected void trigger(Enum call){
        //通过触发器来触发，如果不包含触发事件就返回，比如click
        if (!this.events.containsKey(call)){
            return;
        }
        //在事件注册器里面得到对应事件类型的事件
        Event event = this.events.get(call);
        //事件触发事件的名称
        event.setTrigger(call.toString());
        trigger(event);

    }



}
