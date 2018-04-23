package com.lss.spring.annotion;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LssRequestMapping {
    String value() default "";
}
