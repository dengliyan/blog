package com.xyz.blog.exception;

import com.xyz.blog.result.Result;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1l;

    private Result result;

    public  BusinessException(Result result) {
        this.result = result;
    }
}

