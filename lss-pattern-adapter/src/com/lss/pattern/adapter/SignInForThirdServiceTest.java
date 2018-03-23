package com.lss.pattern.adapter;

import com.lss.pattern.adapter.passport.SignInForThirdService;
import com.lss.pattern.adapter.passport.SignInService;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class SignInForThirdServiceTest {

    public static void main(String[] args) {
        new SignInService().login("","");
        //再不改变原来系统的登录和注册的情况下面，也能够兼容新的需求
        //还可以再加一层策略模式
        new SignInForThirdService().loginForQQ("awsavdvaafwaefavsd");
    }
}
