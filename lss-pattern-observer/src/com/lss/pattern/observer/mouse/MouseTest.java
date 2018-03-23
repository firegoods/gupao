package com.lss.pattern.observer.mouse;

import com.lss.pattern.observer.core.Event;

import java.lang.reflect.Method;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class MouseTest {

    public static void main(String[] args) {
        /**
         *
         * var input = document.getElementById("username");
         *
         * input.addListener("click",function(){
         *
         *     alert("鼠标点击了username");
         * });
         *
         *
         *
         *
         *
         */
        //观察者和被观察者没有必然的联系
        //只有在事件注册的才产生联系

        //这种方式是为了将鼠标的事件函数和回调函数直接解耦
        try {
            MouseEventCallback mouseEventCallback = new MouseEventCallback();

            Method onClick = MouseEventCallback.class.getMethod("click", Event.class);

            Mouse mouse = new Mouse();
            mouse.addListener(MouseEventTypes.ON_CLICK,mouseEventCallback,onClick);

            mouse.click();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
