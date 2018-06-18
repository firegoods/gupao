package com.lss.demo.rmi.rpc;

import com.lss.demo.rmi.rpc.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private IServiceDiscovery iServiceDiscovery;
    private String version;

    public RemoteInvocationHandler(IServiceDiscovery iServiceDiscovery,String version) {
        this.iServiceDiscovery = iServiceDiscovery;
        this.version = version;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request=new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(version);

        String serviceAddress = iServiceDiscovery.discovery(request.getClassName());//根据接口名称拿到对应的服务地址
        //通过tcp传输协议进行传输
        TCPTransport tcpTransport=new TCPTransport(serviceAddress);
        //发送请求
        return tcpTransport.send(request);
    }
}
