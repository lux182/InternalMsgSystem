package com.msg.validator;


public class EmailValidator extends CustomValidator<Email>{

	@Override
	public void initialize(Email constraintAnnotation) {
		this.max=constraintAnnotation.max();
		this.min=constraintAnnotation.min();
		this.lengthmsg=constraintAnnotation.lengthmsg();
		this.regexp=constraintAnnotation.regexp();
		this.regexpmsg=constraintAnnotation.regexpmsg();
		this.emptyable=constraintAnnotation.emptyable();
	}
	
}
