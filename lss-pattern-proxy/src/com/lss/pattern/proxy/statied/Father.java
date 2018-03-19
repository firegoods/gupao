package com.lss.pattern.proxy.statied;

/**
 * Created by liushaoshuai on 2018/3/19.
 */
public class Father {

    private Person person;

    public Father(Person person) {
        this.person = person;
    }

    public void findLvoe(){
        System.out.println("根据你的要求物色对象！");
        this.person.findLove();
        System.out.println("双方父母是不是同意！");
    }
}
