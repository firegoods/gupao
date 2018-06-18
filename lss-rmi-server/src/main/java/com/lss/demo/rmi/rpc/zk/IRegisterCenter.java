package com.lss.demo.rmi.rpc.zk;

/**
 * 全路径
 */
public interface IRegisterCenter {

    public  void register(String serviceName, String serviceAddress);
}
