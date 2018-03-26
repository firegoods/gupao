package com.lss.pattern.proxy.jdk;

import com.lss.pattern.proxy.statied.Person;

/**
 * Created by liushaoshuai on 2018/3/26.
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        XiaoMing xiaoMing = new XiaoMing();
        Person person = (Person)new JDKMeipo().getInstance(xiaoMing);
        person.findLove();
    }
}
