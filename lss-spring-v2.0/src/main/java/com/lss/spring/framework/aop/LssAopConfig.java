package com.lss.spring.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//只是对application中对expression的封装
//目标代理对象的一个方法要增强
//由自已实现的业务逻辑去增强
//配置文件的目的：告诉spring，那些类的那些方法需要增强，增强的内容是什么
//对配置文件中所体现的内容进行封装
public class LssAopConfig {
    //一个pointcut方法对应2个aspect方法
    //思考一下如果是环绕通知我们应该怎么做？
    //以目标对象的Method作为key，需要增强内容作为value
    private Map<Method,LssAspect> points = new HashMap<Method, LssAspect>();

    //???
    //这边碰上一个和handlemapping一样的问题 反射调用的时候对象找不到了
    public void put(Method target,Object aspect,Method[] points){

        this.points.put(target,new LssAspect(aspect,points));

    }

    public LssAspect get(Method method){
        return this.points.get(method);
    }

    public boolean contains(Method method){
        return this.points.containsKey(method);
    }

    //对增强的代码进行封装
    public class LssAspect{
        private Object aspect;//待会将LogAspect这个对象那赋值给它
        private Method[] points;//会将LogAspect的before和after方法赋值进行

        public LssAspect(Object aspect, Method[] points) {
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public Method[] getPoints() {
            return points;
        }
    }


}
