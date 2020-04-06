package com.songzuedu.mybatis.annotation;

import java.lang.annotation.*;

/**
 * <p>用于注解拦截器，指定拦截的方法</p>
 *
 * @author gengen.wang
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {

    String value() default "";

}
