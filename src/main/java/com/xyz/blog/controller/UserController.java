package com.xyz.blog.controller;

import com.xyz.blog.data.model.User;
import com.xyz.blog.entity.LoginEntity;
import com.xyz.blog.exception.BusinessException;
import com.xyz.blog.result.JSONResult;
import com.xyz.blog.result.Result;
import com.xyz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller

public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/login2")
    public String login2()  {
        throw new BusinessException(Result.SYSTEM_ERROR);

    }

    @PostMapping("/login")
    @ResponseBody
    public JSONResult doLogin(HttpServletResponse response, @Valid LoginEntity model) {
        if (userService.Login(response, model.getName(), model.getPassword())) {
            return JSONResult.SUCCESS;
        }
        return JSONResult.error(Result.SYSTEM_ERROR);
    }
}
