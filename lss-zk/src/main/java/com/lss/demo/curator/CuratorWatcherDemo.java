package com.lss.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

public class CuratorWatcherDemo {
    public static void main(String[] args) throws IOException {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString("192.168.52.5:2181,192.168.52.6:2181,192.168.52.7:2181").
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                namespace("curator").build();

        curatorFramework.start();

        //addListenerWithNodeCache(curatorFramework,"/lss");
        //addListenerWithPathChildCache(curatorFramework,"/lss");

        addListenerWithTreeCache(curatorFramework,"/lss");

        System.in.read();


        /**
         * PathChildrenCache 监听一个节点下的节点做创建、删除、更新监听
         *
         * NodeCache 监听一个节点的创建、更新
         *
         * TreeCache 综合PathChildrenCache和TreeCache
         *
         *
         */
    }

    public static void addListenerWithTreeCache(CuratorFramework curatorFramework,String path){
        try {
            TreeCache treeCache = new TreeCache(curatorFramework, path);
            TreeCacheListener treeCacheListener = new TreeCacheListener() {
                public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                    System.out.println("receive event: "+ treeCacheEvent.getType());
                }
            };
            treeCache.getListenable().addListener(treeCacheListener);
            treeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void addListenerWithPathChildCache(CuratorFramework curatorFramework,String path){
        try {
            final PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework,path,false);
            PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                    System.out.println("receive event: "+pathChildrenCacheEvent.getType());
                }
            };
            childrenCache.getListenable().addListener(childrenCacheListener);
            childrenCache.start(PathChildrenCache.StartMode.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addListenerWithNodeCache(CuratorFramework curatorFramework,String path){
        try {
            final NodeCache nodeCache = new NodeCache(curatorFramework,path,false);
            NodeCacheListener nodeCacheListener = new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    System.out.println("receive event: "+nodeCache.getCurrentData().getPath());
                }
            };
            nodeCache.getListenable().addListener(nodeCacheListener);
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
