package com.lss.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context = new  ClassPathXmlApplicationContext("dubbo-client.xml");
        for (int i=0;i<10;i++){
            IHello iHello = (IHello) context.getBean("iHello");

            System.out.println(iHello.sayHello("lss"));
            Thread.sleep(10);
        }

        System.out.println("启动完成");
        System.in.read();
    }
}
