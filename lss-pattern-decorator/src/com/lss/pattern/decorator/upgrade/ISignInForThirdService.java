package com.lss.pattern.decorator.upgrade;

import com.lss.pattern.decorator.old.ISignInService;
import com.lss.pattern.decorator.old.ResultMsg;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public interface ISignInForThirdService extends ISignInService {

    @Override
    ResultMsg login(String username, String password);

    @Override
    ResultMsg register(String username, String password);

    ResultMsg loginQQ(String openId);

    ResultMsg loginWeiChat(String openId);

    ResultMsg loginToken(String token);

    ResultMsg loginForRegister(String name,String password);



}
