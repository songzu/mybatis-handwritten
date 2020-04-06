package com.songzuedu.mybatis.interceptor;

import com.songzuedu.mybatis.annotation.Intercepts;
import com.songzuedu.mybatis.plugin.Interceptor;
import com.songzuedu.mybatis.plugin.Invocation;
import com.songzuedu.mybatis.plugin.Plugin;

import java.util.Arrays;

/**
 * <p>自定义插件</p>
 *
 * @author gengen.wang
 **/
@Intercepts("query")
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        Class pojo = (Class) invocation.getArgs()[2];
        System.out.println("插件输出：SQL：[" + statement + "]");
        System.out.println("插件输出：Parameters：" + Arrays.toString(parameter));

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
