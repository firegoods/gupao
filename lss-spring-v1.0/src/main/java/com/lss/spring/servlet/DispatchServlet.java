package com.lss.spring.servlet;

import com.lss.demo.mvc.DemoAction;
import com.lss.spring.annotion.LssAutowried;
import com.lss.spring.annotion.LssController;
import com.lss.spring.annotion.LssService;
import sun.awt.SunHints;

import javax.print.DocFlavor;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DispatchServlet extends HttpServlet {

    private Properties configContext = new Properties();

    private List<String> classNames = new ArrayList<String>();

    private Map<String,Object> beanMap = new ConcurrentHashMap<String, Object>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======调用doPost方法=====");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
       //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));


        //加载
        doScanner(configContext.getProperty("scanPackage"));


        //注册
        doRegister();

        //依赖注入
        doAutoWried();

        DemoAction demoAction = (DemoAction) beanMap.get("demoAction");
        demoAction.query(null, null ,"lss");




    }

    private void doAutoWried() {
        if (beanMap.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry:beanMap.entrySet()){
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields){
                if (!field.isAnnotationPresent(LssAutowried.class)){
                    continue;
                }
                    LssAutowried autowried = field.getAnnotation(LssAutowried.class);

                    String beanName = autowried.value().trim();
                    if ("".equals(beanName)){
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);

                    try {
                        field.set(entry.getValue(),beanMap.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } finally {
                    }

            }
        }

    }

    private void doRegister(){
        if (classNames.isEmpty()){
            return;
        }
        try {
            for (String className:classNames){
                Class<?> clazz = Class.forName(className);
                //在spring中用多个子方法来处理
                if(clazz.isAnnotationPresent(LssController.class)){
                    String beanName = lowerFirstCase(clazz.getSimpleName());

                    //在这里spring不会直接put instance，而是put一个BeanDefinition
                    beanMap.put(beanName,clazz.newInstance());

                }else if (clazz.isAnnotationPresent(LssService.class)){
                    LssService service = clazz.getAnnotation(LssService.class);
                    //默认使用类名首字母注入
                    //如果自已定义了一个beanName，那么优先使用自已定义的beanName
                    //如果是一个接口,使用接口的类型去自动注入

                    //在spring中同样会分别调用不同方法autowriedByName autowriedByType

                    String beanName = service.value();
                    //如果没有值就默认用类名
                    if ("".equals(beanName.trim())){
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    beanMap.put(beanName,instance);

                    //看是否是接口
                    Class<?>[] clazzs = clazz.getInterfaces();
                    for (Class i: clazzs){
                        beanMap.put(i.getName(),instance);
                    }

                }else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }

    //根据properity文件的路径去扫描对应的类文件
    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\." ,"/"));
        File classDir = new File(url.getFile());
        for (File file:classDir.listFiles()){
            if (file.isDirectory()){
                doScanner(packageName+"."+file.getName());
            }else{
                //突然想到依赖注入是不是也要这样递归否则上面的对象还未创建下面的注入开始了，肯定会报错

                classNames.add(packageName + "." + file.getName().replace(".class",""));
            }

        }



    }

    //定位相当于spring里面的reader，找到对应xml文件，这里我们用properties文件替代
    private void doLoadConfig(String location) {
        InputStream is =  this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        try {
            configContext.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null!=is){
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
