package hungry;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class Hungry {
    /**
     * 饿汉式:在类加载的时候立即初始化
     *
     * 优点：1、绝对的线程安全，不管类加载的时候就new 出来了
     * 2、没有任何锁，效率比较高
     *
     * 缺点：1、不管用不用都占用内存空间
     *
     * */

    //先静态后动态
    // 先属性后方法
    // 先上后下

    /**
     * 私有的构造方法，禁止其他线程在new
     * 思考：如果这个私有的构造方法被暴力破解怎么办？，比如：java的反射。
     */
    private Hungry(){

    }

    /**
     * private 防止其他线程修改
     * static 将成员变量由对象所属变成类所属
     * final 禁止再次更改
     */
    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }


    //这个static 是不是可以去掉？
 }
