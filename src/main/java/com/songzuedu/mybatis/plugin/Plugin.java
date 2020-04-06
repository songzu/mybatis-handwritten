package com.songzuedu.mybatis.plugin;

import com.songzuedu.mybatis.annotation.Intercepts;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 代理类，用于代理被拦截对象
 * 同时提供了创建代理类的方法
 * </p>
 *
 * @author gengen.wang
 **/
public class Plugin implements InvocationHandler {
    //被代理对象
    private Object target;
    //拦截器（插件）
    private Interceptor interceptor;

    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    /**
     * 对被代理对象进行代理，返回代理类
     *
     * @param obj
     * @param interceptor
     * @return
     */
    public static Object wrap(Object obj, Interceptor interceptor) {
        Class clazz = obj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new Plugin(obj, interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 自定义的插件上有@Intercepts注解，指定了拦截的方法
        if (interceptor.getClass().isAnnotationPresent(Intercepts.class)) {
            // 如果是被拦截的方法，则进入自定义拦截器的逻辑
            if (method.getName().equals(interceptor.getClass().getAnnotation(Intercepts.class).value())) {
                return interceptor.intercept(new Invocation(target, method, args));
            }
        }
        // 非被拦截方法，执行原逻辑
        return method.invoke(target, method, args);
    }

}
