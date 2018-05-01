package com.lss.spring.framework.webmvc.servlet;

import com.lss.spring.framework.context.LssApplicationContext;
import com.lss.spring.framework.webmvc.LssHandlerAdapter;
import com.lss.spring.framework.webmvc.LssHandlerMapping;
import com.lss.spring.framework.webmvc.LssModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DispatchServlet extends HttpServlet {


    private final String LOCATION = "contextConfigLocation";

    //思考为什么使用HandlerMapping，经典之处在什么地方
    //HandlerMapping是最核心也是最经典的
    //它牛逼到干掉了Structs和WebWork等MVC框架
    private List<LssHandlerMapping> handlerMappings = new ArrayList<LssHandlerMapping>();

    private List<LssHandlerAdapter> handlerAdapters = new ArrayList<LssHandlerAdapter>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath,"").replaceAll("/+", "/");
        /*LssHandlerMapping  handler = handlerMappings.get(url);*/

        //对象.方法名字
        //对象要在IOC容器中获取
        //method.invoke();
       /* try {
            LssModelAndView mv = (LssModelAndView) handler.getMethod().invoke(handler.getController(),null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/



        //doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp){
        LssHandlerMapping handler = getHandler(req);
        LssHandlerAdapter ha = getHandlerAdapter(handler);
        LssModelAndView mv =  ha.handle(req,resp,handler);

        processDispatchResult(resp, mv);
    }

    private void processDispatchResult(HttpServletResponse resp, LssModelAndView mv) {
        //调用viewreslover的viewreslover
    }


    private LssHandlerAdapter getHandlerAdapter(LssHandlerMapping lssHandlerMapping) {
        return null;
    }

    LssHandlerMapping getHandler(HttpServletRequest req){
        return null;
    }



    @Override
    public void init(ServletConfig config) throws ServletException {
        //相当于把IOC容器初始化了
        LssApplicationContext lssApplicationContext = new LssApplicationContext(config.getInitParameter(LOCATION));

        initStrategies(lssApplicationContext);
    }

    protected void initStrategies(LssApplicationContext context) {
        //springMVC的9大组件
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        //需要
        //controller中requestmapping和method的关系的
        initHandlerMappings(context);
        //需要
        //动态匹配method参数，类型转化，动态赋值
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        //需要
        //解析模板文件
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    private void initFlashMapManager(LssApplicationContext context) { }
    private void initRequestToViewNameTranslator(LssApplicationContext context) { }
    private void initHandlerExceptionResolvers(LssApplicationContext context) { }
    private void initThemeResolver(LssApplicationContext context) { }
    private void initLocaleResolver(LssApplicationContext context) { }
    private void initMultipartResolver(LssApplicationContext context) { }

    private void initViewResolvers(LssApplicationContext context) {
    }

    private void initHandlerAdapters(LssApplicationContext context) {
    }

    //将controller中配置与RequestMapping中的Method进行一一对应
    private void initHandlerMappings(LssApplicationContext context) {
        //按照我们的理解这里应该是一个Map
        //Map<String, Method>
        //map.put(url, method)
    }


}
