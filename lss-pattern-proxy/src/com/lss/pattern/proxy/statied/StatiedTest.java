package com.lss.pattern.proxy.statied;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liushaoshuai on 2018/3/19.
 */
public class StatiedTest {

    public static void main(String[] args) {
        //这个父亲只能帮儿子找对象
        //他不能朋友儿子、女儿、侄女找对象
        Father father = new Father(new Son());
        father.findLvoe();
        String a = "aaaa";
        String b = "bbbb";
        Father father1 = new Father(new Son());
        System.out.println(father.equals(father1));

        Map<String,Object> map = new HashMap();
        for (Map.Entry<String,Object> entry:map.entrySet()){
            entry.getKey();
            entry.getValue();
        }
        Object obj = map.get("userNmae");
    }

}
