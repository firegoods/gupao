package com.lss.spring.framework.annotion;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LssAutowried {
    String value() default "";
}
