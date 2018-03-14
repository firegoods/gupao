package test;

import hungry.Hungry;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class HungryTest {

    public static void main(String[] args) {
        int count = 5000;
        final CountDownLatch latch = new CountDownLatch(count);

        long start = System.currentTimeMillis();

        for(int i=0;i<count;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Hungry hungry = Hungry.getInstance();
                    System.out.println(System.currentTimeMillis() + ": " + hungry);

                }
            }.start();
            latch.countDown();


        }
        long end = System.currentTimeMillis();
        System.out.println("end - start: " + (end-start));//200并发大约20-30毫秒


    }
}
