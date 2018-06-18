package com.lss.demo.rmi.rpc.zk;

import com.lss.demo.rmi.rpc.zk.loadbalance.LoadBalance;
import com.lss.demo.rmi.rpc.zk.loadbalance.RandomLoanBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

public class ServiceDiscoveryImpl implements IServiceDiscovery {

    private  List<String> resps  = new ArrayList<String>();

    private String address;

    private CuratorFramework curatorFramework;

    {

    }

    public ServiceDiscoveryImpl(String address) {
        this.address = address;

        curatorFramework = CuratorFrameworkFactory.
                builder().
                connectString(address).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                build();
        curatorFramework.start();
    }

    public String discovery(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH+"/"+serviceName;
        try {

            resps = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常："+e);
        }
        //动态的发现服务节点的变化
        registerWatcher(path);
        //负载均衡机制
        LoadBalance loadBalance = new RandomLoanBalance();
        return loadBalance.selectHost(resps);//返回调用的服务地址
    }

    private void registerWatcher(final String path){
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,path,true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                resps = curatorFramework.getChildren().forPath(path);
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);

        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw  new RuntimeException("注册pathchild watch 异常"+e);
        }

    }
}
