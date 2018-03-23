package com.lss.pattern.adapter.passport;

import com.lss.pattern.adapter.Member;
import com.lss.pattern.adapter.ResultMsg;

/**
 * Created by liushaoshuai on 2018/3/23.
 */
public class SignInService {

    /**
     * 老版系统的登录方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg register (String username, String password){
        return new ResultMsg("200","注册成功",new Member());
    }


    /**
     * 老版系统的登录方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login (String username, String password){
        return new ResultMsg("200","登录成功",new Member());
    }
}
