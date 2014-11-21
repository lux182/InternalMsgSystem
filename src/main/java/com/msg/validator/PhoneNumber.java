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
@Constraint(validatedBy={PhoneNumberValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
	String message() default Hint.PHONE_NUMBER_COULD_NOT_BE_EMPTY;
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    boolean emptyable() default false;
    int max() default 50;
    int min() default 1;
    String lengthmsg() default Hint.PHONE_NUMBER_LENGTH_ERROR;
    String regexp() default "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
    String regexpmsg() default Hint.PHONE_NUMBER_COMPOSE_ERROR;
}
