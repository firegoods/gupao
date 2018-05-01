package com.lss.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LssHandlerAdapter {

    /**
     *
     * @param request
     * @param response
     * @param handler 为什么要把HanddlerMapping 传入进来？ 因为包含了controller、method、url等信息
     * @return
     */
    public LssModelAndView handle(HttpServletRequest request, HttpServletResponse response, LssHandlerMapping handler){
        //根据用户请求的参数信息，更method中参数进行动态匹配
        //response 传进来的目的只有一个，就是将servlet中的response赋值给方法参数，仅此而已

        //modelandview 只有当用户传过来的modelandview为null时，才会new
        return null;

    }
}
