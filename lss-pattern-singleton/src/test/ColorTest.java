package test;

import register.Color;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class ColorTest {

    public static void main(String[] args) {
        int count = 1;
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
                    Color object = Color.WHITE;
                    System.out.println(System.currentTimeMillis() + ": " + object.toString());
                }
            }.start();
            latch.countDown();

        }


        long end  = System.currentTimeMillis();
        System.out.println("end - start : " + (end - start));


    }
}
