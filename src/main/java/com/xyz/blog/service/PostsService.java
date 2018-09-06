package com.xyz.blog.service;

import com.xyz.blog.data.mapper.PostsMapper;
import com.xyz.blog.data.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    PostsMapper postsMapper;

    public List<Posts> FindAll() {
        return postsMapper.FindAll();
    }

    public Posts FindById(long id){
        return postsMapper.FindById(id);
    }

    public long Add(String title,String content){
        return postsMapper.Add(title,content);
    }
}
