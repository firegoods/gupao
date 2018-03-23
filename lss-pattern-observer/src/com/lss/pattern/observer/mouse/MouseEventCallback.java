package com.lss.pattern.observer.mouse;

import com.lss.pattern.observer.core.Event;

/**
 *
 * 观察者
 *
 * 回调响应的逻辑由自已实现
 * Created by liushaoshuai on 2018/3/23.
 */
public class MouseEventCallback {

    public void click(Event e){
        System.out.println("这里是鼠标单击之后要执行的逻辑");
        System.out.println("=============触发鼠标单击事件=============\n"+e);
    }


    public void doubleClick(Event e){
        System.out.println("这里是鼠标双击之后要执行的逻辑");
        System.out.println("=============触发鼠标双击事件=============\n"+e);
    }
}
