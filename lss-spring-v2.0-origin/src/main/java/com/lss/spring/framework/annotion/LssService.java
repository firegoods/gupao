package com.lss.spring.framework.annotion;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LssService {
    String value() default "";
}
