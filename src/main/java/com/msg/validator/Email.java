package com.msg.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.msg.utils.SystemMessage.Hint;

@Documented
@Constraint(validatedBy={EmailValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
	String message() default Hint.EMAIL_COULD_NOT_BE_EMPTY;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    boolean emptyable() default false;
    int max() default 50;
    int min() default 1;
    String lengthmsg() default Hint.EMAIL_LENGTH_ERROR;
    String regexp() default "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    String regexpmsg() default Hint.EMAIL_COMPOSE_ERROR;
}
