package com.lss.spring.annotion;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LssRequestParam {
    String value() default "";
}
