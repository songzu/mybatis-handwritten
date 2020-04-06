package com.songzuedu.mybatis.annotation;

import java.lang.annotation.*;

/**
 * <p>注解方法，配置SQL语句</p>
 *
 * @author gengen.wang
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {

    String value() default "";

}
