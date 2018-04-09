package com.lss.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liushaoshuai on 2018/4/9.
 */
public class LssConfiguration {
    public <T> T getMapper(Class<T> clazz,SqlSsesion sqlSsesion) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new LssMapperProxy(sqlSsesion));
    }


    /**
     * 模拟解析xml
     */
    static class TestMapperXml{
        public static final String namespace = "com.lss.mybatis.v1.TestMapper";

        public static final Map<String, String> methodSqlMapping = new HashMap<String, String>();

        static {
            methodSqlMapping.put("selectByPrimaryKey","select * from test where id=%d");
        }

    }

}
