package com.songzuedu.mybatis.executor;

import com.songzuedu.mybatis.parameter.ParameterHandler;
import com.songzuedu.mybatis.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>封装JDBC Statement，用于操作数据库</p>
 *
 * @author gengen.wang
 **/
public class StatementHandler {

    private ResultSetHandler resultSetHandler = new ResultSetHandler();

    public <T> T query(String statement, Object[] parameter, Class pojo) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Object result = null;

        try {
            //1.打开连接
            conn = getConnection();
            //2.执行查询
            preparedStatement = conn.prepareStatement(statement);
            ParameterHandler parameterHandler = new ParameterHandler(preparedStatement);
            parameterHandler.setParameters(parameter);
            preparedStatement.execute();
            //3.获取结果集
            result = resultSetHandler.handle(preparedStatement.getResultSet(), pojo);
            return (T) result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取连接
     *
     * @return
     */
    private Connection getConnection() {
        String driver = Configuration.properties.getString("jdbc.driver");
        String url = Configuration.properties.getString("jdbc.url");
        String username = Configuration.properties.getString("jdbc.username");
        String password = Configuration.properties.getString("jdbc.password");
        Connection conn = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
