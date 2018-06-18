package com.lss.demo;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZKDatabase;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ConnectionDemo {

    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zooKeeper = new ZooKeeper("192.168.52.5:2181,192.168.52.6:2181,192.168.52.7:2181", 4000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    if(Event.KeeperState.SyncConnected.equals(watchedEvent.getState())){
                        countDownLatch.countDown();
                    }

                }
            });
            countDownLatch.await();
            System.out.println(zooKeeper.getState());//CONNECTING

            //Thread.sleep(1000);
            //System.out.println(zooKeeper.getState());//CONNECTED
            zooKeeper.create("/zk-persis-1","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Stat stat = new Stat();
            byte[] data = zooKeeper.getData("/zk-persis-1",null,stat);
            System.out.println(new String(data));

            stat = zooKeeper.setData("/zk-persis-1","1".getBytes(),stat.getVersion());
            byte[] data1 = zooKeeper.getData("/zk-persis-1",null,stat);
            System.out.println(new String(data1));

            zooKeeper.delete("/zk-persis-1",stat.getVersion());


            zooKeeper.close();

            //watcher 是一次性操作
            //有钱只有 getdata exists getchild

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
