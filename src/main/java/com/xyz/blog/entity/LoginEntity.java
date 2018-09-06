package com.xyz.blog.entity;

import com.xyz.blog.validation.IsMobile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LoginEntity {

    @IsMobile()
    private String name;

    @NotNull
    @Length(min = 6, max = 32,message = "密码长度不正确")
    private String password;
}
