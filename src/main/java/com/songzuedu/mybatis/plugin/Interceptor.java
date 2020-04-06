package com.songzuedu.mybatis.plugin;

/**
 * <p>拦截器接口，所有自定义拦截器必须实现此接口</p>
 *
 * @author gengen.wang
 **/
public interface Interceptor {

    /**
     * 插件的核心逻辑实现
     *
     * @param invocation
     * @return
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 对被拦截对象进行代理
     *
     * @param target
     * @return
     */
    Object plugin(Object target);

}
