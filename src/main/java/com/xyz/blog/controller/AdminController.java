package com.xyz.blog.controller;

import com.xyz.blog.config.LoginRequired;
import com.xyz.blog.data.model.Posts;
import com.xyz.blog.data.model.User;
import com.xyz.blog.result.JSONResult;
import com.xyz.blog.result.Result;
import com.xyz.blog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@LoginRequired
public class AdminController {

    @Autowired
    PostsService postsService;

    @GetMapping("/admin/")
    public String Main(Model model, User user) {
        model.addAttribute("user", user);
        // System.out.println(user.getUserName());
        return "admin/default";
    }

    @GetMapping("/admin/posts/")
    public String Posts(Model model){
        List<Posts> posts_list=postsService.FindAll();
        model.addAttribute("posts_list",posts_list);
        return "admin/posts";
    }

    @GetMapping("/admin/posts/add")
    public String PostsAdd(){
        return "admin/posts_add";
    }

    @PostMapping("/admin/posts/add")
    @ResponseBody
    public JSONResult doPostsAdd(@RequestParam("title") String title,@RequestParam("html_content") String content) {
        long id=postsService.Add(title,content);
        return id>0?JSONResult.success(id):JSONResult.error(Result.OPER_ERROR);
    }
}
