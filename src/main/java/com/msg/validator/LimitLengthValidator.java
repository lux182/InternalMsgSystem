package com.msg.validator;


public class LimitLengthValidator extends CustomValidator<LimitLength>{

	@Override
	public void initialize(LimitLength constraintAnnotation) {
		this.emptyable=constraintAnnotation.emptyable();
		this.max=constraintAnnotation.max();
		this.min=constraintAnnotation.min();
		this.lengthmsg=constraintAnnotation.lengthmsg();
	}
	
}
