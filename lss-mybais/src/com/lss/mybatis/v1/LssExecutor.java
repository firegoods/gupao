package com.lss.mybatis.v1;

/**
 * Created by liushaoshuai on 2018/4/9.
 */
public interface LssExecutor {
    public <T> T query(String statement, String params);
}
