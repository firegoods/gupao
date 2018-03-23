package com.lss.pattern.decorator;

import com.lss.pattern.decorator.old.SignInService;
import com.lss.pattern.decorator.upgrade.ISignInForThirdService;
import com.lss.pattern.decorator.upgrade.SignInForThirdService;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class SignInServiceTest {

    public static void main(String[] args) {
        //对比适配器和装饰器模式的区别？
        //适配器模式个人感觉更适合这种多种登录方式，并且兼容老的模式
        //装饰器模式虽然也能达到这个目的，但是它更是一种方法的增强


        //装饰器原来的功能依旧对外开放，
        //新的功能也能用

        ISignInForThirdService iSignInForThirdService = new SignInForThirdService(new SignInService());
        iSignInForThirdService.loginQQ("weafaesfsfasf");

        //spring 常用的装饰器英文命名 decorator wapper

    }
}
