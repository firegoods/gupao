package com.lss.demo.rmi.rpc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotion {
    /**
     * 对外发布的服务接口地址
     * @return
     */
    Class<?> value();

    String version() default "";

}
