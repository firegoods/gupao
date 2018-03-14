package test;

import lazy.LazyOne;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class LazyOneTest {


    public static void main(String[] args) {
        int  count = 5000;
        final CountDownLatch latch = new CountDownLatch(count);

        long start = System.currentTimeMillis();
        for (int i=0;i<count;i++){
            new Thread(){

                @Override
                public void run() {

                    try {
                        //new 一个发令枪，当发令枪的count等于0的时候，200个线程一起new ，测试200并发
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LazyOne lazyOne = LazyOne.getInstance();
                    System.out.println(System.currentTimeMillis() + ": " + lazyOne);
                }
            }.start();
            latch.countDown();
        }
        long end  = System.currentTimeMillis();
        System.out.println("end - start: " + (end - start));

    }
}
