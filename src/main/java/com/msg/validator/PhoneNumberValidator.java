package com.msg.validator;

public class PhoneNumberValidator extends CustomValidator<PhoneNumber> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    	this.lengthmsg=constraintAnnotation.lengthmsg();
		this.min=constraintAnnotation.min();
		this.max=constraintAnnotation.max();
        this.regexp = constraintAnnotation.regexp();
        this.regexpmsg = constraintAnnotation.regexpmsg();
        this.emptyable = constraintAnnotation.emptyable();
    }

}
