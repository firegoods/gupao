package com.lss.pattern.proxy.jdk;

import com.lss.pattern.proxy.statied.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liushaoshuai on 2018/3/26.
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        try {
        XiaoMing xiaoMing = new XiaoMing();
        Person person = (Person)new JDKMeipo().getInstance(xiaoMing);
        person.findLove();

        byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
        os.write(bytes);
        os.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
