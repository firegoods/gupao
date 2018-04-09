package com.lss.mybatis.v1;

/**
 * 用户通过sqlsession来操作数据库
 * Created by liushaoshuai on 2018/4/9.
 */
public class SqlSsesion {
    private LssConfiguration lssConfiguration;
    private LssExecutor lssExecutor;
    //1、搭主线
    //怎么样在sqlsession、configuration、executor之间建立联系
    //模仿spring通过构造函数或者getter、setter方法
    //2、sqlsession是怎么操作的 MapperProxy
    //3、根据时序图 MapperProxy是调用sqlsession的selectone方法的


    public SqlSsesion(LssConfiguration lssConfiguration, LssExecutor lssExecutor) {
        this.lssConfiguration = lssConfiguration;
        this.lssExecutor = lssExecutor;
    }

    //根据james大法的时序图，通过梳理出来的时序图来写Mapper

    /**
     * getMapper
     *
     * @param clazz
     */
    public <T> T getMapper(Class<T> clazz){

        //单一职责
       return lssConfiguration.getMapper(clazz,this);

    }


    /**
     *
     * @param statement sql语句
     * @param params sql的参数
     * @param <T>
     * @return
     */
    public <T> T getSelectOne(String statement, String params){
            return lssExecutor.query(statement, params);

    }

}
