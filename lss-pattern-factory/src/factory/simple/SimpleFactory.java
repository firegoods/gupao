package factory.simple;

import factory.Mengniu;
import factory.Milk;
import factory.Yili;

/**
 * Created by liushaoshuai on 2018/3/9.
 */
public class SimpleFactory {

    public  Milk getMilk(String name){
        if ("yili".equalsIgnoreCase(name)){
            return new Yili();
        }else if ("mengniu".equalsIgnoreCase(name)){
            return new Mengniu();
        }else {
            return null;
        }
    }



}
