package com.lss.pattern.decorator.old;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class SignInService implements ISignInService {

    @Override
    public ResultMsg login(String username, String password) {
        return new ResultMsg("200","登录成功！",new Member());
    }

    @Override
    public ResultMsg register(String username, String password) {
        return new ResultMsg("200","注册成功！",new Member());
    }
}
