package com.lss.pattern.decorator.upgrade;

import com.lss.pattern.decorator.old.ISignInService;
import com.lss.pattern.decorator.old.ResultMsg;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class SignInForThirdService implements ISignInForThirdService {


    private ISignInService iSignInService;

    public SignInForThirdService(ISignInService iSignInService) {
        this.iSignInService = iSignInService;
    }

    @Override
    public ResultMsg login(String username, String password) {
        return this.iSignInService.login(username,password);
    }

    @Override
    public ResultMsg register(String username, String password) {
        return this.iSignInService.register(username,password);
    }

    @Override
    public ResultMsg loginQQ(String openId) {

        //1、openId默认是全局的唯一用户名
        //2、默认密码是qq_empty
        //3、注册，在原来的老的系统注册一个用户
        //4、调用原来的登录方法

        return this.loginForRegister(openId,null);
    }

    @Override
    public ResultMsg loginWeiChat(String openId) {
        return null;
    }

    @Override
    public ResultMsg loginToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForRegister(String name, String password) {
        this.register(name,password);
        return this.login(name,password);
    }
}
