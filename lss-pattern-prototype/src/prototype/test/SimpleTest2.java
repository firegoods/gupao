package prototype.test;

import prototype.simple.CloneTarget2;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class SimpleTest2 {
    public static void main(String[] args) {

        //深克隆，会把基本类型和引用类型都会复制一份，但是这个方式有个弊端，就是要重写克隆方法，同时要手工的clone引用类型数据

        CloneTarget2 cloneTarget = new CloneTarget2();
        cloneTarget.list.add("jim");
        cloneTarget.list.add("tom");

        try {
            CloneTarget2 obj = (CloneTarget2)cloneTarget.clone();
            obj.list.add("123");
            System.out.println("list: " + obj.list.size());
            System.out.println("list: " + cloneTarget.list.size());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }
}
