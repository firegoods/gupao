package com.lss.dubbo;

public class IHelloICluster2mpl implements IHello {
    public String sayHello(String msg) {
        return "cluster2"+msg;
    }
}
