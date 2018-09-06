package com.xyz.blog.controller;

import com.xyz.blog.data.mapper.PostsMapper;
import com.xyz.blog.data.model.Posts;
import com.xyz.blog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostsService postsService;
    //
    @GetMapping("/posts")
    public String postsList(Model model) {
        List<Posts> posts_list = postsService.FindAll();
        model.addAttribute("posts_list",posts_list);
        return "posts/index";
    }

    @GetMapping("/posts/{posts_id}")
    public String postsList(Model model,@PathVariable("posts_id") long id) {
        Posts posts=postsService.FindById(id);
        model.addAttribute("posts",posts);
        return "posts/detail";
    }
}
