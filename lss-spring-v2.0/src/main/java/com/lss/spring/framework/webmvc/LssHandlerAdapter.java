package com.lss.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class LssHandlerAdapter {

    private Map<String, Integer> paramMapping;

    public LssHandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    /**
     *
     * @param request
     * @param response
     * @param handler 为什么要把HanddlerMapping 传入进来？ 因为包含了controller、method、url等信息
     * @return
     */
    public LssModelAndView handle(HttpServletRequest request, HttpServletResponse response, LssHandlerMapping handler) throws Exception{
        //根据用户请求的参数信息，更method中参数进行动态匹配
        //response 传进来的目的只有一个，就是将servlet中的response赋值给方法参数，仅此而已

        //modelandview 只有当用户传过来的modelandview为null时，才会new
        //1、准备好方法的形参列表
        //形参的决定因素：参数的个数、参数的类型、参数的顺序、方法的名字（方法重载）
        Class<?>[] paramTypes = handler.getMethod().getParameterTypes();

        //2、拿到自定义命名参数所在的位置
        //通过url传过来的参数列表
        Map<String, String[]> reqParameterMap = request.getParameterMap();

        //3、构造实参列表
        Object[] paramValues = new Object[paramTypes.length];
        for (Map.Entry<String,String[]> param: reqParameterMap.entrySet()){
            String value = Arrays.toString(param.getValue()).replaceAll("\\[\\]","").replaceAll("\\s","");
            if (!this.paramMapping.containsKey(param.getKey())){continue;}


            int index = this.paramMapping.get(param.getKey());

            //因为在页面中传过来的值是String 但是方法的中的有各种类型
            //我们要对传过来的参数进行类型转换
            paramValues[index] = caseStringValue(value,paramTypes[index]);

        }
        if (this.paramMapping.containsKey(HttpServletRequest.class.getName())){
            int reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }

        if (this.paramMapping.containsKey(HttpServletResponse.class.getName())){
            int respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }



        //4、从handler 中取出controller、method 然后用反射机制进行调用
        Object result = handler.getMethod().invoke(handler.getController(),paramValues);
        if (result == null){
            return null;
        }
        if (handler.getMethod().getReturnType() == LssModelAndView.class){
            return (LssModelAndView) result;
        }else {
            return null;
        }




    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz == String.class){
            return value;
        }else if (clazz == Integer.class){
            return Integer.valueOf(value);
        }else if (clazz == int.class){
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }

    }
}
