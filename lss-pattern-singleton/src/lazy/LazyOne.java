package lazy;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class LazyOne {

    /**
     *懒汉式
     * 1、构造方法私有化
     *
     *
     */

    /**
     * 优点：用的时候才new 对象
     *
     * 缺点：不是线程安全的
     *
     */
    //公共的内存区域
    private static LazyOne lazyOne = null;


    private LazyOne(){

    }


    public static LazyOne getInstance(){
        //如果单列不存在，则new 一个单列
        if (null==lazyOne){
            //没有加锁导致2个线程同时进入会有安全问题
            lazyOne = new LazyOne();
        }
        //如果单列存在，则返回
        return lazyOne;

    }

}
