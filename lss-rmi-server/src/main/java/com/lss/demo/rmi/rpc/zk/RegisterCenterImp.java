package com.lss.demo.rmi.rpc.zk;

import com.lss.demo.rmi.rpc.anno.RpcAnnotion;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

@RpcAnnotion(IRegisterCenter.class)
public class RegisterCenterImp implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.
                builder().
                connectString(ZkConfig.CONNECTION_STR).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                build();
        curatorFramework.start();
    }

    public void register(String serviceName, String serviceAddress) {
        //注册相应的服务
        try {
            String servicePath = ZkConfig.ZK_REGISTER_PATH+"/"+serviceName;
            //判断 /registry/***-service是否存在，不存在则创建
            if (null==curatorFramework.checkExists().forPath(servicePath)){
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).
                        forPath(servicePath,"0".getBytes());
            }
            String addressPath = servicePath+"/"+serviceAddress;
            String rsNode = curatorFramework.
                    create().
                    withMode(CreateMode.EPHEMERAL).
                    forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功："+rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
