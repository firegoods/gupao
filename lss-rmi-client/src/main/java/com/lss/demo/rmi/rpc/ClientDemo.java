package com.lss.demo.rmi.rpc;

import com.lss.demo.rmi.rpc.zk.IServiceDiscovery;
import com.lss.demo.rmi.rpc.zk.ServiceDiscoveryImpl;
import com.lss.demo.rmi.rpc.zk.ZkConfig;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ClientDemo {

    public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery iServiceDiscovery = new ServiceDiscoveryImpl(ZkConfig.CONNECTION_STR);

        RpcClientProxy rpcClientProxy=new RpcClientProxy(iServiceDiscovery);
        for (int i=0;i<10;i++){
            IGpHello hello=rpcClientProxy.clientProxy
                    (IGpHello.class,null);
            System.out.println(hello.sayHello("mic"));
            Thread.sleep(1000);
        }

    }
}
