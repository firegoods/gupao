package com.lss.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {

    public static void main(String[] args) {
        try {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                    connectString("192.168.52.5:2181,192.168.52.6:2181,192.168.52.7:2181").
                    sessionTimeoutMs(4000).
                    retryPolicy(new ExponentialBackoffRetry(1000,3)).
                    namespace("curator").build();

            curatorFramework.start();
            curatorFramework.create().creatingParentsIfNeeded().
                    withMode(CreateMode.PERSISTENT).
                    forPath("/lss/node1","1".getBytes());
            Stat stat = new Stat();

            //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/lss/node1");

            curatorFramework.getData().
                    storingStatIn(stat).
                    forPath("/lss/node1");
            curatorFramework.setData().
                    withVersion(stat.getVersion()).
                    forPath("/lss/node1","21".getBytes());
            curatorFramework.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
