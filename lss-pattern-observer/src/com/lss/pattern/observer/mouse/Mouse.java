package com.lss.pattern.observer.mouse;

import com.lss.pattern.observer.core.EventListener;

/**
 * 被观察者，如果在swing里面我们能想到什么？
 * Created by liushaoshuai on 2018/3/23.
 */
public class Mouse extends EventListener {


    public void click(){
        System.out.println("鼠标单击事件！");
        this.trigger(MouseEventTypes.ON_CLICK);
    }

    public void doubleClick(){
        System.out.println("鼠标双击事件！");
        this.trigger(MouseEventTypes.ON_DOUBLE_CLICK);
    }


}
