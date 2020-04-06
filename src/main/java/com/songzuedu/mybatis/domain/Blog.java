package com.songzuedu.mybatis.domain;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class Blog implements Serializable {
    /**
     * 文章ID
     */
    Long bid;
    /**
     * 文章标题
     */
    String name;
    /**
     * 文章作者ID
     */
    Long authorId;

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
