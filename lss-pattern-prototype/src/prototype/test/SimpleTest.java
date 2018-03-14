package prototype.test;

import prototype.simple.CloneTarget;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class SimpleTest {

    public static void main(String[] args) {

        //浅克隆只会将基本类型克隆，引用类型只会把地址克隆过去，而不是克隆一个new 一个新对象

        CloneTarget cloneTarget = new CloneTarget();
        cloneTarget.list.add("jim");
        cloneTarget.list.add("tom");

        try {
            CloneTarget obj = (CloneTarget)cloneTarget.clone();
            obj.list.add("123");
            System.out.println("list: " + obj.list.size());
            System.out.println("list: " + cloneTarget.list.size());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }
}
