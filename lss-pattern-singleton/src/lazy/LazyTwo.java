package lazy;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class LazyTwo {

    /**
     *
     * 懒汉式
     * 通过synchronize来实现线程安全
     *
     *
     * 缺点：每次只准一个线程进入方法，性能比较低
     */
    private static LazyTwo lazyTwo = null;


    private LazyTwo(){

    }

    public synchronized static LazyTwo getInstance(){//每次只准一个线程进入
        if (null==lazyTwo){//判断是否存在，如果对象存在则不用再次new
            lazyTwo = new LazyTwo();
        }
        return lazyTwo;
    }


    /**
     * 针对第一个懒汉式线程不安全的，通过synchronize来实现线程安全，但是带来了线程性能问题？
     * 怎么破？
     */

}
