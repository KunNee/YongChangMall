package com.YongChang.config.imlog;


import java.lang.annotation.*;

/**
 * YongChang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImLog {

    String type();


    String mark();


}
