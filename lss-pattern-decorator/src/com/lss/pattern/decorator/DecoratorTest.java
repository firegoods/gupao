package com.lss.pattern.decorator;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class DecoratorTest {

    public static void main(String[] args) {
        //不修改原来的类的基础上进行动态的新增新的功能或者覆盖原来的方法
        //该实现保持和原来的类的有层级关系
        //采用装饰器模式
        //装饰器模式更像是一种特殊的适配器

        //jdk中典型的IO类用的装饰器
        InputStream inputStream = null;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
    }
}
