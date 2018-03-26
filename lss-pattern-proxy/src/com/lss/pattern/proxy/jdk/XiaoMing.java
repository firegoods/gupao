package com.lss.pattern.proxy.jdk;

import com.lss.pattern.proxy.statied.Person;

/**
 * Created by liushaoshuai on 2018/3/26.
 */
public class XiaoMing implements Person,PersonFindJob {
    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("升高180cm");

    }

    @Override
    public void findJob() {
        System.out.println("月薪20k-50k");
    }
}
