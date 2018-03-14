package lazy;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class LazyThree {
    /**
     * 针对LazyThree 的性能低下问题做解决
     * 使用double-check+volatile
     *
     */

    /**
     * private 防止被修改 比如：LazyThree.lazyThree = null;
     * static 变成共享的成员变量
     * volatile 内存可见性，防止出现空对象
     *
     * 关于加volatile我们知道，初始化一个对象分为4个环节
     * 1、开辟一个内存空间
     * 2、初始化
     * 3、赋值
     * 4、返回
     * 但是由于指令重排序会导致：2和3可能会调换
     * 这时候就会导致一个问题：
     * ThreadA先执行了3然后执行2，从单线程的角度看赋值之后初始化时没什么问题的
     * 从多线的角度看则会有问题，ThreadB会认为对象已经存在，直接返回了，其实这个时候ThreadA正在初始化
     * 这时候就会出现报错
     *
     */

    private static volatile LazyThree lazyThree = null;

    private LazyThree(){

    }

    public static LazyThree getInstance(){
        if (null==lazyThree){//第一个if判断是用来提升性能的，当对象new出来之后，后面的线程就不用在再次进入synchronize
            synchronized (LazyThree.class){//线程性能安全的
                /**
                 * 第二个if是用来确保单列只被new 一次
                 * 如果ThreadA和ThreadB 同时过了第一if判断，这时ThreadA进入了synchronized ThreadB进入了锁池, ThreadA new了一个对象，然后出synchronized
                 * ThreadB进入synchronized 如果没有第二个if判断则 第二个对象会覆盖第一个对象
                 */
                if (null==lazyThree){
                    lazyThree = new LazyThree();
                }
            }
        }
        return lazyThree;
    }
}
