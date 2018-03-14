package factory.test;

import factory.Milk;
import factory.simple.SimpleFactory;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class SimpleTest {
    public static void main(String[] args) {

        SimpleFactory simpleFactory =new SimpleFactory();
        Milk milk = simpleFactory.getMilk("yili");
        System.out.println(milk.getName());
    }
}
