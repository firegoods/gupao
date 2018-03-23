package com.lss.pattern.decorator.old;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public interface ISignInService {

    public ResultMsg login(String username,String password);

    public ResultMsg register(String username, String password);
}
