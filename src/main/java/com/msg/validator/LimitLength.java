package com.msg.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.msg.utils.SystemMessage.Submit;

@Documented
@Constraint(validatedBy={LimitLengthValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitLength {
	String message() default Submit.SUBMIT_CONTENT_TOO_BIG;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    boolean emptyable() default true;
    int max() default 50;
    int min() default 1;
    String lengthmsg() default Submit.SUBMIT_CONTENT_TOO_BIG;
}
