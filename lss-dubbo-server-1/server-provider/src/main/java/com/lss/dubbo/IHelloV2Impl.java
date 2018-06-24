package com.lss.dubbo;

public class IHelloV2Impl implements IHello {
    public String sayHello(String msg) {
        return "V2.0"+msg;
    }
}
