package com.lss.pattern.delegate.leader;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目经理
 * 权衡boos的事情，来进行员工的委派
 *
 * Created by liushaoshuai on 2018/3/21.
 */
public class Leader implements IEmployee{

    private Map<String,IEmployee> employees = new HashMap<String, IEmployee>();

    public Leader() {
        this.employees.put("加密",new EmployeeA());
        this.employees.put("登录",new EmployeeB());
    }

    @Override
    public void doSomethings(String command) {
        this.employees.get(command).doSomethings(command);
    }
}
