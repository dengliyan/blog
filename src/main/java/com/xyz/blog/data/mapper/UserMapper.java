package com.xyz.blog.data.mapper;

import com.xyz.blog.data.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_name=#{user_name}")
    public User FindByUserName(@Param("user_name") String name);
}
