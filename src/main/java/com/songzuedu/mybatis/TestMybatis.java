package com.songzuedu.mybatis;

import com.songzuedu.mybatis.domain.Blog;
import com.songzuedu.mybatis.mapper.BlogMapper;
import com.songzuedu.mybatis.session.DefaultSqlSession;
import com.songzuedu.mybatis.session.SqlSessionFactory;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        DefaultSqlSession sqlSession = factory.build().openSqlSession();
        // 获取MapperProxy代理
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);

        System.out.println("第一次查询: " + blog);
        System.out.println();
        blog = mapper.selectBlogById(1);
        System.out.println("第二次查询: " + blog);
    }

}
