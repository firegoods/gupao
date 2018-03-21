package com.lss.pattern.delegate.mvc;

import com.lss.pattern.delegate.mvc.controller.MemberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * SpringMVC的分发器
 *
 * Created by liushaoshuai on 2018/3/21.
 */
public class ServletDispatcher {
    private List<Handle> handlerMapping = new ArrayList<Handle>();


    public ServletDispatcher(List<Handle> handlerMapping) {
       try {
           //在mvc中我们是让mvc来扫描我们的controller包下面的类
           Class<?> memberActionClass =  MemberAction.class;
           handlerMapping.add(new Handle().setController(memberActionClass.newInstance())
                   .setMethod(memberActionClass.getMethod("", new Class[]{String.class}))
                   .setUrl("/web/getMemberById.json"));
       }catch (Exception e){
            e.printStackTrace();
       }

    }

    public void doService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        doDispathcer(httpServletRequest,httpServletResponse);
    }
    public void doDispathcer(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        //1、获取用户的请求url
        //如果按照J2EE标准，每个url对应一个Servlet，url由游览器输入
        String uri = httpServletRequest.getRequestURI();

        //Servlet 拿到请求之后，要去做权衡，去做选择
        //根据用户请求的url找到对应的每个类的谋个方法

        //3、通过url去HandleMapping(我们把它默认为是策略常量)

        Handle handle = null;
        for (Handle h:handlerMapping){
            if (uri.equals(h.getUrl())){
                handle = h;
                break;
            }
        }

        //4、将具体任务分发给具体类的方法（通过反射来调用）
        Object object = null;

        try{
            object = handle.method.invoke(handle.controller,httpServletRequest.getParameter("mid"));
        }catch (Exception e){
            e.printStackTrace();
        }
        //5、获得Method的执行的结果，通过Response将结果返回



    }




    //创建一个handle，相当于被委派者的引用
    class Handle{
        private Object controller;
        private String url;
        private Method method;

        public Handle setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Handle setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handle setMethod(Method method) {
            this.method = method;
            return this;
        }
    }


}
