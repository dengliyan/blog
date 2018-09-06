package com.xyz.blog.data.mapper;

import com.xyz.blog.data.model.Posts;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostsMapper {

    @Select("select * from posts order by create_date desc")
    public List<Posts> FindAll();

    @Select("select count(0) from posts")
    public long FindAllCount();


    @Select("select * from posts where id=#{id}")
    public Posts FindById(@Param("id") long id);

    @Insert("insert into posts(title,html_content,create_date) values(#{title},#{html_content},now())")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long Add(@Param("title") String title,@Param("html_content") String content);
}
