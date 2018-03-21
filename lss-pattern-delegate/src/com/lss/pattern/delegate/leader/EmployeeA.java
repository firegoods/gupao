package com.lss.pattern.delegate.leader;

/**
 * Created by liushaoshuai on 2018/3/21.
 */
public class EmployeeA implements IEmployee {

    @Override
    public void doSomethings(String command) {
        System.out.println("我是员工A，我正在做"+command+"的事情！");
    }
}
