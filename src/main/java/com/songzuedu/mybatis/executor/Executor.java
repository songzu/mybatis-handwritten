package com.songzuedu.mybatis.executor;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public interface Executor {

    <T> T query(String statement, Object[] parameter, Class pojo);

}
