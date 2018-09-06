package com.xyz.blog.data.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    public static final String COOK_NAME_TOKEN="token";

    public static final String REQUEST_CONTEXT_USER="REQUEST_CONTEXT_USER";
    private String userName;
    private String userPassword;
}
