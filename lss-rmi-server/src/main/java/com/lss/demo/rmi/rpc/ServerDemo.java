package com.lss.demo.rmi.rpc;

import com.lss.demo.rmi.rpc.zk.IRegisterCenter;
import com.lss.demo.rmi.rpc.zk.RegisterCenterImp;

import java.io.IOException;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IGpHello iGpHello=new GpHelloImpl();
        IGpHello iGpHello2=new GpHelloImpl2();

        IRegisterCenter iRegisterCenter = new RegisterCenterImp();
        RpcServer rpcServer=new RpcServer(iRegisterCenter,"127.0.0.1:8080");
        rpcServer.bind(iGpHello,iGpHello2);

        rpcServer.publisher();
        System.in.read();
    }
}
