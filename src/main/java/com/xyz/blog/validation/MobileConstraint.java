package com.xyz.blog.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileConstraint implements ConstraintValidator<IsMobile,String> {

    private boolean required;
    @Override
    public void initialize(IsMobile context) {
        this.required = context.required();
    }

    private static final Pattern mobile_pattern = Pattern.compile("^1\\d{10}$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!required && StringUtils.isEmpty(s)) {
            return true;
        }
        Matcher m = mobile_pattern.matcher(s);
        return m.matches();
    }
}
