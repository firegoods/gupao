package lazy;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class LazyFour {
    /**
     * 不管是饿汉式还是懒汉式(double-check+volatile)都存在问题
     * 饿汉式比较占用内存空间
     * LazyThree 还是存在一定的性能问题
     * 思考：我们能不能用一种其他方式来实现呢？
     *比如刚刚的饿汉式：最好能在类被加载，方法被调用之前new 一个单列
     *
     *
     * 总结：兼顾了饿汉式的内存浪费和懒汉式的synchronized的问题
     */


    private LazyFour(){

    }

    //static 是为了使单列的空间共享
    //final 是为了使其他类在继续此类时不能被重写
    public static final LazyFour getInstance(){
        return LazyFourHolder.lazyFour;
    }

    //默认是不加载的
    private static class  LazyFourHolder{
        private static final LazyFour lazyFour = new LazyFour();
    }

}
