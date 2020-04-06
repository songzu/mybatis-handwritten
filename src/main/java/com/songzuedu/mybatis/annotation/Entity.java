package com.songzuedu.mybatis.annotation;

import java.lang.annotation.*;

/**
 * <p>用于注解接口，以映射返回的实体类</p>
 *
 * @author gengen.wang
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {

    Class<?> value();

}
