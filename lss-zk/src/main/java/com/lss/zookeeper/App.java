package com.lss.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class App {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    DistributeLock distributeLock = new DistributeLock();
                    distributeLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread-"+i).start();
            countDownLatch.countDown();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
