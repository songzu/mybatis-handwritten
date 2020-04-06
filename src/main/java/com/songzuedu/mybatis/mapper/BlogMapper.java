package com.songzuedu.mybatis.mapper;

import com.songzuedu.mybatis.annotation.Entity;
import com.songzuedu.mybatis.annotation.Select;
import com.songzuedu.mybatis.domain.Blog;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
@Entity(Blog.class)
public interface BlogMapper {

    /**
     * 根据主键查询
     *
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = ?")
    public Blog selectBlogById(Integer bid);

}
