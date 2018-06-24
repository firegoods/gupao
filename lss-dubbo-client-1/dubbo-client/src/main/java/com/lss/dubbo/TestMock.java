package com.lss.dubbo;

public class TestMock implements IHello {
    public String sayHello(String msg) {
        return "系统繁忙！";
    }
}
