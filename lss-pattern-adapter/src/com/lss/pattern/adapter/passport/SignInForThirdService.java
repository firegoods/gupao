package com.lss.pattern.adapter.passport;

import com.lss.pattern.adapter.ResultMsg;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class SignInForThirdService extends SignInService {

    public ResultMsg loginForQQ(String openId){
        //1、openId默认是全局的唯一，我们可以把openId当成一个加长版的用户名
        //2、密码默认是qq_empty
        //3、注册（在原来的系统创建一个用户）
        //4、调用原来的登录方法

        return loginForRegister(openId, null);

    }

    public ResultMsg loginForWeiChat(String openId){
        return loginForRegister(openId,null);
    }

    public ResultMsg loginForTelphone(String telPhone){
        return loginForRegister(telPhone, null);
    }

    public ResultMsg loginForToken(String token){
        //通过token拿到用户的信息，然后再重新登录一下
        return null;
    }





    public ResultMsg loginForRegister(String username, String password) {
        super.register(username,null);
        return super.login(username, null);
    }
}
