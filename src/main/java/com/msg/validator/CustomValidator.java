package com.msg.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public abstract class CustomValidator<A extends Annotation> implements ConstraintValidator<A,String>{
    
    protected Boolean emptyable=null;
    protected Integer max=null;
    protected Integer min=null;
    protected String lengthmsg=null;
    protected String regexp=null;
    protected String regexpmsg=null;
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       if(emptyable!=null){
           if(StringUtils.isEmpty(value)){
               return emptyable;
           }
           if(max!=null&&min!=null){
               if(value.length()<min||value.length()>max){
                   context.disableDefaultConstraintViolation();
                   context.buildConstraintViolationWithTemplate(lengthmsg).addConstraintViolation();
                   return false;
               }
           }
           if(regexp!=null){
               if(!Pattern.matches(regexp, value)){
                   context.disableDefaultConstraintViolation();
                   context.buildConstraintViolationWithTemplate(regexpmsg).addConstraintViolation();
                   return false;
               }
           }
           return true;
       }
        return false;
    }
    
    
}
