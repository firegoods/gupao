package factory.test;

import factory.Milk;
import factory.func.Factory;
import factory.func.YiliFactory;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class FunctionTest {

    public static void main(String[] args) {
        //工厂方法，标准化的生产模式
        //优点：将产品放在专门的工厂中，让产品和工厂解耦，只要工厂提供对应的标准
        //缺点：每当要用新的工程时候，用户要修改用户代码。只是做到工厂-产品的对修改关闭，对新增开放，
        //没做到工厂-用户的对修改关闭，对新增开放
        Factory factory = new YiliFactory();
        Milk milk = factory.getMilk();
        System.out.println(milk.getName());
    }
}
