package com.lss.dubbo;

public class IHelloICluster1mpl implements IHello {
    public String sayHello(String msg) {
        return "cluster1"+msg;
    }
}
