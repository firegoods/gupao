package com.lss.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DistributeLock implements Lock,Watcher {

    private ZooKeeper zooKeeper = null;
    private String ROOT_LOCK="/locks";//定义跟节点
    private String WAIT_LOCK;//等待前一个锁
    private String CURRENT_LOCK;//表示当前锁

    private CountDownLatch countDownLatch;

    public DistributeLock() {

        try {

            zooKeeper = new ZooKeeper("192.168.52.5:2181",4000, this);//watcher传this因为当前类实现了watcher接口
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if(null==stat){
                zooKeeper.create(ROOT_LOCK,"0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean tryLock() {
        try {
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK+"/","0".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName()+"->"+CURRENT_LOCK+", 尝试竞争锁");
            List<String> childrens = zooKeeper.getChildren(ROOT_LOCK, false);
            SortedSet<String> sortedSet = new TreeSet();
            for (String child:childrens){
                sortedSet.add(ROOT_LOCK+"/"+child);
            }
            String firstNode = sortedSet.first();//获得当前节点中最小的子节点
             SortedSet<String> lessThenMe = ((TreeSet<String>)sortedSet).headSet(CURRENT_LOCK);
             if (CURRENT_LOCK.equals(firstNode)){//通过当前节点和子节点中最小的进行对比，如果相等表示获得锁成功
                return true;
             }
             if (!lessThenMe.isEmpty()){
                 WAIT_LOCK = lessThenMe.last();//获得比当前节点更小的最后一个节点设置给wait_lock

             }


        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void lock() {
        try {
            if (this.tryLock()){
                System.out.println(Thread.currentThread().getName()+ "->" +CURRENT_LOCK +"->获得锁成功");
                return;
            }
            waitForLock(WAIT_LOCK);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private boolean waitForLock(String prev) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(prev,true);//监听当前节点的上一个节点
        if(stat!=null){
            System.out.println(Thread.currentThread().getName()+"->等待锁"+"/"+prev+"释放");
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"获得锁成功" );
        }
        return true;


    }

    public void lockInterruptibly() throws InterruptedException {

    }



    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        System.out.println(Thread.currentThread().getName()+"释放锁");
        try {
            zooKeeper.delete(CURRENT_LOCK, -1);
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent watchedEvent) {
        if (null!=countDownLatch){
            countDownLatch.countDown();
        }
    }
}
