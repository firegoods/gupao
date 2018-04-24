package com.lss.spring.framework.context;

import com.lss.spring.demo.mvc.DemoAction;
import com.lss.spring.framework.annotion.LssAutowried;
import com.lss.spring.framework.annotion.LssController;
import com.lss.spring.framework.annotion.LssService;
import com.lss.spring.framework.beans.BeanDefinition;
import com.lss.spring.framework.beans.BeanPostProcessor;
import com.lss.spring.framework.beans.BeanWrapper;
import com.lss.spring.framework.context.support.BeanDefinitionReader;
import com.lss.spring.framework.core.BeanFactory;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LssApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    private Map<String,Object> beanCacheMap = new HashMap<String, Object>();

    private Map<String,BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();

    public LssApplicationContext(String ... configLocations) {
        this.configLocations = configLocations;
        refesh();

    }

    private void refesh() {
        //定位
        this.reader = new BeanDefinitionReader(this.configLocations);

        //加载
         List<String> beanDefinitions = reader.loadBeanDefinitions();


        //注册
        doRegister(beanDefinitions);

       //依赖注入（lazyInit=false），要执行依赖注入
        //这里自动调用getbean方法
        doAutowrited();

        DemoAction demoAction = (DemoAction) this.getBean("demoAction");
        demoAction.query(null,null,"lss most nb!");



    }
    //开始进行依赖注入
    private void doAutowrited() {

        for(Map.Entry<String,BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()){
                Object beanWrapper = getBean(beanName);
                System.out.println("=====1111========="+beanName+" ,"+beanWrapper);
            }

        }

        for (Map.Entry<String,BeanWrapper> beanWrapperEntry:this.beanWrapperMap.entrySet()){
            //进行属性注入
            populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getWrapperInstance());
        }
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
                System.out.println("======================"+instance+" ,"+autowiredBeanName+","+this.beanWrapperMap.get(autowiredBeanName));
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
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        String className = beanDefinition.getBeanClassName();

        BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

        Object instance =  instantionBean(beanDefinition);
        if (null ==instance){return null;}
        //在实例化之前调用一次
        beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

        BeanWrapper beanWrapper = new BeanWrapper(instance);
        beanWrapper.setBeanPostProcessor(beanPostProcessor);
        this.beanWrapperMap.put(beanName,beanWrapper);

        //在实例化之h后调用一次
        beanPostProcessor.postProcessAfterInitialization(instance,beanName);

        //populateBean(beanName,instance);
        //通过这样一调用给了我们留了可操作的空间
        return this.beanWrapperMap.get(beanName).getWrapperInstance();
    }

    //传一个beandefinition 就返回一个bean
    private Object instantionBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();

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
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition!=null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i:interfaces){
                    //如果是多个实现类智能覆盖
                    //spring就是这么sb
                    //这里可以自定义名字
                    this.beanDefinitionMap.put(i.getName(),beanDefinition);
                }
                //到这里初始化就完毕了


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }



}
