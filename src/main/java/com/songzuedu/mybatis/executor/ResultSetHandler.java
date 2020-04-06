package com.songzuedu.mybatis.executor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>结果集处理器</p>
 *
 * @author gengen.wang
 **/
public class ResultSetHandler {

    private final static String UNDERLINE = "_";

    public <T> T handle(ResultSet resultSet, Class type) {
        // 直接调用Class的方法产生一个实例
        Object pojo = null;
        try {
            pojo = type.newInstance();
            // 遍历结果集
            if (resultSet.next()) {
                for (Field field : pojo.getClass().getDeclaredFields()) {
                    setValue(pojo, field, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) pojo;
    }

    /**
     * 通过反射给属性赋值
     *
     * @param pojo
     * @param field
     * @param rs
     */
    private void setValue(Object pojo, Field field, ResultSet rs) {
        try {
            Method setMethod = pojo.getClass().getMethod("set" + firstWordCapital(field.getName()), field.getType());
            setMethod.invoke(pojo, getResult(field, rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据反射判断类型，从ResultSet中取对应类型参数
     *
     * @param field
     * @param rs
     * @return
     */
    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        // 驼峰转下划线
        String dataName = humpToUnderline(field.getName());
        if (Integer.class == type) {
            return rs.getInt(dataName);
        } else if (String.class == type) {
            return rs.getString(dataName);
        } else if (Long.class == type) {
            return rs.getLong(dataName);
        } else if (Boolean.class == type) {
            return rs.getBoolean(dataName);
        } else if (Double.class == type) {
            return rs.getDouble(dataName);
        } else {
            return rs.getString(dataName);
        }
    }

    /**
     * Java驼峰命名转数据库下划线转
     *
     * @param para
     * @return
     */
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;//定位
        if (!para.contains(UNDERLINE)) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, UNDERLINE);
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 单词首字母大写
     *
     * @param word
     * @return
     */
    private String firstWordCapital(String word) {
        String first = word.substring(0, 1);
        String tail = word.substring(1);
        return first.toUpperCase() + tail;
    }

}
