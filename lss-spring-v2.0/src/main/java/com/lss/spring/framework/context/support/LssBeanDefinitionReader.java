package com.lss.spring.framework.context.support;

import com.lss.spring.framework.beans.LssBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


//对配置文件进行查找、读取、解析
public class LssBeanDefinitionReader {

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    private List<String> registerBeanClasses = new ArrayList<String>();

    public LssBeanDefinitionReader(String ...locations) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));

        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String packageName) {

        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());

        for (File file: classDir.listFiles()){
            if (file.isDirectory()){
                doScanner(packageName + "." + file.getName());
            }else {
                registerBeanClasses.add(packageName + "." +file.getName().replace(".class",""));
            }
        }

    }

    public List<String> loadBeanDefinitions() {
        return  this.registerBeanClasses;
    }

    //每注册一个classname，就返回一个beandefinition
    // 只是为了对配置信息做进一步包装
    public LssBeanDefinition registerBean(String className) {
        if (this.registerBeanClasses.contains(className)){
            LssBeanDefinition lssBeanDefinition = new LssBeanDefinition();
            lssBeanDefinition.setBeanClassName(className);
            lssBeanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".")+1)));
            return lssBeanDefinition;



        }
        return null;
    }

    private String lowerFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return config;
    }
}
