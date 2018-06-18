package com.lss.demo.rmi.rpc;

import com.lss.demo.rmi.IHelloService;
import com.lss.demo.rmi.rpc.anno.RpcAnnotion;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 *
 */
@RpcAnnotion(IGpHello.class)
public class GpHelloImpl implements IGpHello{

    public String sayHello(String msg) {
        return "1 am 8080 Hello , "+msg;
    }
}
