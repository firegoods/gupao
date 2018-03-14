package register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liushaoshuai on 2018/3/12.
 */
public class BeanFactory {

    //1、new 一个concurrenthashmap 将对象以键值对的形式放进去
    //2、使用map的put 将对象放进去
    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public synchronized static Object getBean(String className){
        Object obj = null;
        try {
            if (!ioc.containsKey(className)){
                obj = Class.forName(className).newInstance();
                ioc.put(className,obj);
            }else {
                obj = ioc.get(className);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;


    }




}
