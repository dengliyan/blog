package com.xyz.blog.data.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Posts {
    private long id;
    private String title;
    private String htmlContent;
    private Date createDate;
    private Date updateDate;
}
