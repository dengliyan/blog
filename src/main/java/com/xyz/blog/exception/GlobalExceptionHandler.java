package com.xyz.blog.exception;

import com.alibaba.fastjson.JSON;
import com.xyz.blog.result.JSONResult;
import com.xyz.blog.result.Result;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.hibernate.validator.constraints.Length;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response,Model model,  Exception exception)  throws Exception {
        exception.printStackTrace();
        response.setCharacterEncoding("UTF-8");
        JSONResult result = JSONResult.error(Result.SYSTEM_ERROR);
        if (exception instanceof BusinessException) {
            BusinessException ex = (BusinessException) exception;
            result = JSONResult.error(ex.getResult());
        } else if (exception instanceof BindException) {
            BindException ex = (BindException) exception;
            List<ObjectError> errors = ex.getAllErrors();
            List<FieldError> fieldErrors = ex.getFieldErrors();
            //FieldError error = fieldErrors.get(0);
            ObjectError error = errors.get(0);
            result = JSONResult.error(Result.BIND_EXCEPTION.format(error.getDefaultMessage()));
        }
        if (!(request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            //如果是没有登录，则跳回至登录页面
            if(result.getRet()==Result.USER_NOT_LOGIN.getRet()
                    ||result.getRet()==Result.USER_TOKEN_ILLEGAL.getRet()) {
                return "redirect:/login?returnUrl=" + URLEncoder.encode( request.getRequestURI(),"utf-8");
            }
            response.setStatus(500);
            model.addAttribute("error", result.getMsg());
            return "error";
        }
        response.reset();
        response.setContentType("application/json");
        OutputStream out = response.getOutputStream();
        String val = JSON.toJSONString(result);
        out.write(val.getBytes(Charset.forName("utf-8")));
        out.flush();
        return null;
    }
}
