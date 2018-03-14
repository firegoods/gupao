package test;

import lazy.LazyThree;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class LazyThreeTest {

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
                    LazyThree lazyThree = LazyThree.getInstance();
                    System.out.println(System.currentTimeMillis() + ": " + lazyThree);
                }
            }.start();
            latch.countDown();
        }

        long end  = System.currentTimeMillis();
        System.out.println("end - start : " + (end - start));


    }
}
