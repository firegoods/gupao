package com.lss.dubbo;

public class IHelloV1Impl implements IHello {
    public String sayHello(String msg) {
        return "V1.0"+msg;
    }
}
