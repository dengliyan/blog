package com.xyz.blog.service;

import com.xyz.blog.cache.keys.UserKey;
import com.xyz.blog.cache.redis.RedisService;
import com.xyz.blog.data.mapper.UserMapper;
import com.xyz.blog.data.model.User;
import com.xyz.blog.exception.BusinessException;
import com.xyz.blog.result.Result;
import com.xyz.blog.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    public User FindUserByName(String name) {
        return userMapper.FindByUserName(name);
    }


    public boolean Login(HttpServletResponse response, String name, String pass) {
        User user = userMapper.FindByUserName(name);
        if (user == null) {
            throw  new BusinessException(Result.USER_NOT_EXISTS);
        }
        if (!user.getUserPassword().equals(pass)) {
            throw new BusinessException(Result.USER_PASSWORD_ERROR);
        }
        //数据保存到cookies中
        String token= UUIDUtil.uuid();
        redisService.set(UserKey.TOKEN,token,user);
        //添加cookie
        Cookie cookie = new Cookie(User.COOK_NAME_TOKEN,token);
        cookie.setPath("/");
        cookie.setMaxAge(UserKey.TOKEN.getSeconds());
        response.addCookie(cookie);
        return  true;
    }
}
