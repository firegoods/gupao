package com.lss.mybatis.v1;

import com.lss.mybatis.beans.Test;

/**
 * 测试我们写的框架
 * Created by liushaoshuai on 2018/4/9.
 */
public class Bootstrap {
    public static void main(String[] args) {
        SqlSsesion sqlSsesion = new SqlSsesion(new LssConfiguration(),new LssSimpleExecutor());
        TestMapper testMapper = sqlSsesion.getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey("1");
        System.out.println(test.toString());

    }
}
