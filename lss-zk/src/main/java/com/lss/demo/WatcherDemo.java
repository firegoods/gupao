package com.lss.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class WatcherDemo {

    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final ZooKeeper zooKeeper = new ZooKeeper("192.168.52.5:2181,192.168.52.6:2181,192.168.52.7:2181", 4000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("全局的watcher："+watchedEvent.getType());
                    if(Event.KeeperState.SyncConnected.equals(watchedEvent.getState())){
                        countDownLatch.countDown();
                    }

                }
            });
            countDownLatch.await();

            zooKeeper.create("/zk-persis-lss","1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Stat stat = zooKeeper.exists("/zk-persis-lss", new Watcher() {
                public void process(WatchedEvent watchedEvent) {

                    try {
                        //默认会使用全局的watcher
                        zooKeeper.exists(watchedEvent.getPath(),true);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(watchedEvent.getType()+"->"+watchedEvent.getPath());
                }
            });
            stat = zooKeeper.setData("/zk-persis-lss","2".getBytes(),stat.getVersion());

            zooKeeper.delete("/zk-persis-lss",stat.getVersion());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
