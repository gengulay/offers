package com.gengulay.spring.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

	private int min;

	@Override
	public void initialize(ValidEmail constraintAnnotaion) {
		min = constraintAnnotaion.min();

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email.length() <= min) {
			return false;
		}

		if (EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}

		return true;
	}

}
