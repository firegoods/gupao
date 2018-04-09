package com.lss.mybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * mapper代理
 * Created by liushaoshuai on 2018/4/9.
 */
public class LssMapperProxy implements InvocationHandler{

    private SqlSsesion sqlSsesion;

    public LssMapperProxy(SqlSsesion sqlSsesion) {
        this.sqlSsesion = sqlSsesion;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过接口的调用方法去找到xml的对应sql
        //怎么拿？只有充分的了解了阉割版的jdk动态代理才能写出如此代码
        if (method.getDeclaringClass().getName().equals(LssConfiguration.TestMapperXml.namespace)){
            String sql = LssConfiguration.TestMapperXml.methodSqlMapping.get(method.getName());
            //执行sql
            return sqlSsesion.getSelectOne(sql,String.valueOf(args[0]));
        }


        return method.invoke(this,args);
    }
}
