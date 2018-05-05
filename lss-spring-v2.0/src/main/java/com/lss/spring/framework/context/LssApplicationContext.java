package com.lss.spring.framework.context;

import com.lss.spring.framework.annotion.LssAutowried;
import com.lss.spring.framework.annotion.LssController;
import com.lss.spring.framework.annotion.LssService;
import com.lss.spring.framework.aop.LssAopConfig;
import com.lss.spring.framework.beans.LssBeanDefinition;
import com.lss.spring.framework.beans.LssBeanPostProcessor;
import com.lss.spring.framework.beans.LssBeanWrapper;
import com.lss.spring.framework.context.support.LssBeanDefinitionReader;
import com.lss.spring.framework.core.LssBeanFactory;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LssApplicationContext extends LssDefaultListableBeanFactory implements LssBeanFactory {

    private String[] configLocations;

    private LssBeanDefinitionReader reader;



    private Map<String,Object> beanCacheMap = new HashMap<String, Object>();

    private Map<String,LssBeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, LssBeanWrapper>();

    public LssApplicationContext(String ... configLocations) {
        this.configLocations = configLocations;
        refesh();

    }

    private void refesh() {
        //定位
        this.reader = new LssBeanDefinitionReader(this.configLocations);

        //加载
         List<String> beanDefinitions = reader.loadBeanDefinitions();


        //注册
        doRegister(beanDefinitions);

       //依赖注入（lazyInit=false），要执行依赖注入
        //这里自动调用getbean方法
        doAutowrited();

        /*DemoAction demoAction = (DemoAction) this.getBean("demoAction");
        demoAction.query(null,null,"lss most nb!");*/



    }
    //开始进行依赖注入
    private void doAutowrited() {

        for(Map.Entry<String,LssBeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()){
                Object beanWrapper = getBean(beanName);
                System.out.println(beanWrapper.getClass());
                //System.out.println("=====1111========="+beanName+" ,"+beanWrapper);
            }

        }

        for (Map.Entry<String,LssBeanWrapper> beanWrapperEntry:this.beanWrapperMap.entrySet()){
            //进行属性注入
            //populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getWrapperInstance());
            populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getOriginalInstance());
        }
        //System.out.println("populateBean==========");
    }

    private void populateBean(String beanName,Object instance) {
        Class<?> clazz = instance.getClass();

        //不是所有的属性都要注入：不是所有的牛奶都叫特伦舒
        if(!(clazz.isAnnotationPresent(LssController.class)|| clazz.isAnnotationPresent(LssService.class))){
            return;

        }
        Field[] fields = clazz.getDeclaredFields();

        for (Field field:fields){
            if (!field.isAnnotationPresent(LssAutowried.class)){
                continue;
            }
            LssAutowried lssAutowried = field.getAnnotation(LssAutowried.class);
            String autowiredBeanName = lssAutowried.value().trim();

            if ("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);

            try {
                //System.out.println("======================"+instance+" ,"+autowiredBeanName+","+this.beanWrapperMap.get(autowiredBeanName));
                field.set(instance,this.beanWrapperMap.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
            }

        }

    }

    //依赖注入
    //读取BeanDefiniton中的信息，通过反射实例化
    //这里spring并不是把最原始的对象放出去，而是使用一个BeanWrapper进行一次包装
    //装饰其器模式：
    //1、保留原本的OOP关系
    //2、需要对它进行扩展，增强（为以后的AOP打下基础）
    @Override
    public Object getBean(String beanName){

        LssBeanDefinition lssBeanDefinition = this.beanDefinitionMap.get(beanName);
        String className = lssBeanDefinition.getBeanClassName();

        LssBeanPostProcessor lssBeanPostProcessor = new LssBeanPostProcessor();

        Object instance =  instantionBean(lssBeanDefinition);
        if (null ==instance){return null;}
        //在实例化之前调用一次
        lssBeanPostProcessor.postProcessBeforeInitialization(instance,beanName);

        LssBeanWrapper lssBeanWrapper = new LssBeanWrapper(instance);
        try {
            lssBeanWrapper.setAopConfig(instantiationAopConfig(lssBeanDefinition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lssBeanWrapper.setLssBeanPostProcessor(lssBeanPostProcessor);
        this.beanWrapperMap.put(beanName, lssBeanWrapper);

        //在实例化之h后调用一次
        lssBeanPostProcessor.postProcessAfterInitialization(instance,beanName);

        //populateBean(beanName,instance);
        //通过这样一调用给了我们留了可操作的空间
        return this.beanWrapperMap.get(beanName).getWrapperInstance();
    }

    //传一个beandefinition 就返回一个bean
    private Object instantionBean(LssBeanDefinition lssBeanDefinition) {
        Object instance = null;
        String className = lssBeanDefinition.getBeanClassName();

        try {
            //因为根据class才能确定一个类是否有实例
            if (this.beanCacheMap.containsKey(className)){
                instance = this.beanCacheMap.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className,instance);


            }
            return instance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
        }
        return null;

    }

    //这里将beandefinitions注册到beandefinitionmap中去
    private void doRegister(List<String> beanDefinitions) {

        try {
            for (String className: beanDefinitions){
                Class<?> beanClass = Class.forName(className);

                //如果是一个接口，是不能实例化的
                //用它的实现类来实例化

                if (beanClass.isInterface()){
                    continue;
                }
                LssBeanDefinition lssBeanDefinition = reader.registerBean(className);
                if (lssBeanDefinition !=null){
                    this.beanDefinitionMap.put(lssBeanDefinition.getFactoryBeanName(), lssBeanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i:interfaces){
                    //如果是多个实现类智能覆盖
                    //spring就是这么sb
                    //这里可以自定义名字
                    this.beanDefinitionMap.put(i.getName(), lssBeanDefinition);
                }
                //到这里初始化就完毕了


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }


    public int getBeanDefinitionCount() {
        /*return getBeanFactory().getBeanDefinitionCount();*/
        return this.beanDefinitionMap.size();
    }


    public String[] getBeanDefinitionNames() {
        /*return getBeanFactory().getBeanDefinitionNames();*/
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }



    //初始化
    private LssAopConfig instantiationAopConfig(LssBeanDefinition beanDefinition) throws Exception{
        LssAopConfig config = new LssAopConfig();
        String expression = reader.getConfig().getProperty("pointcut");
        String[] before = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = reader.getConfig().getProperty("aspectAfter").split("\\s");


        String className = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(className);
        Pattern pattern = Pattern.compile(expression);
        Class aspectClass = Class.forName(before[0]);
        //这里得到的方法是原生的方法
        for (Method method: clazz.getMethods()){
            //public .* com\.lss\.spring\.demo\.service\..*Service\..*\(.*\)
            //public java.lang.String com.lss.spring.demo.service.impl.ModifyService.add(java.lang.String,java.lang.String)
            Matcher matcher = pattern.matcher(method.toString());
            if (matcher.matches()){
                //能满足切面规则的类添加到aop中
                config.put(method,aspectClass.newInstance(),new Method[]{aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1])});
            }
        }

        return config;

    }

}
