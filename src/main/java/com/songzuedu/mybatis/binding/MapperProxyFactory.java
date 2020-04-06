package com.songzuedu.mybatis.binding;

import com.songzuedu.mybatis.session.DefaultSqlSession;

import java.lang.reflect.Proxy;

/**
 * <p>用于产生MapperProxy代理类</p>
 *
 * @author gengen.wang
 **/
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    private Class object;

    public MapperProxyFactory(Class<T> mapperInterface, Class object) {
        this.mapperInterface = mapperInterface;
        this.object = object;
    }

    public T newInstance(DefaultSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                new MapperProxy(sqlSession, object));
    }

}
