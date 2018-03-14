package factory.test;

import factory.abstr.AbatractFactory;
import factory.abstr.MilkFactory;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class AbstracTest {

    public static void main(String[] args) {
        //通过抽象工厂用户只有选择产品的权限，是的用户和产品解耦，同时所有的产品都在一起管理
        //产品-工厂 对修改关闭，对新增开放
        //工厂-用户  对修改关闭，对新增开放

        AbatractFactory factory = new MilkFactory();
        System.out.println(factory.getMengniu().getName());
    }
}
