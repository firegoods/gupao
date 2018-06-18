package com.lss.demo.rmi.rpc;

import com.lss.demo.rmi.rpc.zk.IServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class RpcClientProxy {


    private IServiceDiscovery iServiceDiscovery;

    public RpcClientProxy(IServiceDiscovery iServiceDiscovery) {
        this.iServiceDiscovery = iServiceDiscovery;
    }

    /**
     * 创建客户端的远程代理。通过远程代理进行访问
     * @param interfaceCls
     * @param
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<T>
                                     interfaceCls,String version
                             ){
        //使用到了动态代理。
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls},new RemoteInvocationHandler(iServiceDiscovery,version));
    }




}
