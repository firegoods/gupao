package test;

import lazy.LazyTwo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class LazyTwoTest {

    public static void main(String[] args) {
        int count = 5000;
        final CountDownLatch latch = new CountDownLatch(count);

        long start = System.currentTimeMillis();
        for (int i = 0;i<count;i++){
            new Thread(){
                @Override
                public void run(){
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LazyTwo lazyTwo = LazyTwo.getInstance();
                    System.out.println(System.currentTimeMillis() + ": " + lazyTwo);
                }
            }.start();
            latch.countDown();
        }

        long end  = System.currentTimeMillis();
        System.out.println("end - start : " + (end - start));


    }
}
