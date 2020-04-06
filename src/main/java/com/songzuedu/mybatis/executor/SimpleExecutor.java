package com.songzuedu.mybatis.executor;

/**
 * <p>数据库查询</p>
 *
 * @author gengen.wang
 **/
public class SimpleExecutor implements Executor {

    @Override
    public <T> T query(String statement, Object[] parameter, Class pojo) {
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(statement, parameter, pojo);
    }

}
