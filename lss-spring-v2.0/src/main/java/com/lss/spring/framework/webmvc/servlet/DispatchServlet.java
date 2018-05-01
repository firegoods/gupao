package com.lss.spring.framework.webmvc.servlet;

import com.lss.spring.framework.annotion.LssController;
import com.lss.spring.framework.annotion.LssRequestMapping;
import com.lss.spring.framework.annotion.LssRequestParam;
import com.lss.spring.framework.context.LssApplicationContext;
import com.lss.spring.framework.webmvc.LssHandlerAdapter;
import com.lss.spring.framework.webmvc.LssHandlerMapping;
import com.lss.spring.framework.webmvc.LssModelAndView;
import com.lss.spring.framework.webmvc.LssViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatchServlet extends HttpServlet {


    private final String LOCATION = "contextConfigLocation";

    //思考为什么使用HandlerMapping，经典之处在什么地方
    //HandlerMapping是最核心也是最经典的
    //它牛逼到干掉了Structs和WebWork等MVC框架
    private List<LssHandlerMapping> handlerMappings = new ArrayList<LssHandlerMapping>();

    private Map<LssHandlerMapping,LssHandlerAdapter> handlerAdapters = new HashMap<LssHandlerMapping, LssHandlerAdapter>();

    private List<LssViewResolver> viewResolvers = new ArrayList<LssViewResolver>();

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


        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[\\]", "")
                    .replaceAll("\\s","\r\n")  + "@LSS");
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp)throws Exception{
            //更据用户请求的url来获取一个handler
            LssHandlerMapping handler = getHandler(req);
            if (handler == null){
                resp.getWriter().write("404 not found\r\n @LSS");
            }

            LssHandlerAdapter ha = getHandlerAdapter(handler);

            LssModelAndView mv =  ha.handle(req,resp,handler);

            processDispatchResult(resp, mv);

    }

    private void processDispatchResult(HttpServletResponse resp, LssModelAndView mv) throws Exception{
        //调用viewreslover的viewreslover
        if (null == mv){
            return;
        }
        if (this.viewResolvers.isEmpty()){
            return;
        }
        for (LssViewResolver viewResolver: this.viewResolvers){
            if (!mv.getViewName().equals(viewResolver.getViewName())){continue;}

            String out = viewResolver.viewResolver(mv);
            if (out !=null){
                resp.getWriter().write(out);
                break;
            }
        }
    }


    private LssHandlerAdapter getHandlerAdapter(LssHandlerMapping lssHandlerMapping) {
        if (this.handlerAdapters.isEmpty()){return null;}
        return this.handlerAdapters.get(lssHandlerMapping);

    }

    LssHandlerMapping getHandler(HttpServletRequest req){
        if (this.handlerMappings.isEmpty()){return null;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+","/");
        for (LssHandlerMapping handler: this.handlerMappings){
            Matcher matcher = handler.getPattern().matcher(url);
            if(!matcher.matches()){continue;}
            return handler;
        }


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
        //在页面敲一个http://localhost/first.html
        //解决一个页面名字和模板文件关联问题
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);

        for (File template: templateRootDir.listFiles()){
            this.viewResolvers.add(new LssViewResolver(template.getName(), template));
        }



    }

    //所有的方法的参数实现动态配置
    private void initHandlerAdapters(LssApplicationContext context) {
        //在初始化阶段 ，将参数的类型或者名字按照一定的顺序保存一下
        //因为后面用反射的调用，传参是数组
        //可以通过记录这些参数的位置的index，挨个从数组中填值，这样的话就和参数的顺序无关
        for (LssHandlerMapping handlerMapping: this.handlerMappings){
            //每一个方法有一个参数列表，那么保存的是形参列表
            Map<String,Integer> paramMapping = new HashMap<String, Integer>();

            //这里只是命名参数
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0;i < pa.length; i++){
                for (Annotation a: pa[i]){
                    if (a instanceof LssRequestParam){
                        String paramName = ((LssRequestParam) a).value();
                        //如果参数是注解的
                        if (!"".equals(paramName.trim())){
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }
            //接下来处理非命名参数
            //这边我们只处理Request、Response
            Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i=0; i < paramTypes.length; i++){
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class|| type == HttpServletResponse.class){
                    paramMapping.put(type.getName(),i);
                }

            }

            this.handlerAdapters.put(handlerMapping, new LssHandlerAdapter(paramMapping));



        }
    }

    //将controller中配置与RequestMapping中的Method进行一一对应
    private void initHandlerMappings(LssApplicationContext context) {
        //按照我们的理解这里应该是一个Map
        //Map<String, Method>
        //map.put(url, method)

        //首先从容器中取到所有的bean
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName: beanNames){
            Object controller = context.getBean(beanName);

            Class<?> clazz = controller.getClass();
            //但是不是所有的bean是要取
            if (!clazz.isAnnotationPresent(LssController.class)){
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(LssRequestMapping.class)){
                LssRequestMapping requestMapping = clazz.getAnnotation(LssRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //扫描所有的public方法
            Method[] methods = clazz.getMethods();
            for (Method method: methods){
                if (!method.isAnnotationPresent(LssRequestMapping.class)){
                    continue;
                }
                LssRequestMapping requestMapping = method.getAnnotation(LssRequestMapping.class);
                String regex = ("/" + baseUrl +requestMapping.value().replaceAll("\\*",".*")).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new LssHandlerMapping(pattern, controller, method));
                System.out.println("Mapping: " + regex + "," + method);
            }
        }
    }


}
