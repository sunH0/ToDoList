package com.mission.todolist.domain.Item.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumTypeValid, String>  {

	private EnumTypeValid annotation;

	@Override
	public void initialize(EnumTypeValid constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Object[] enumValues = this.annotation.target().getEnumConstants();

		if (this.annotation.isNull() && value.isEmpty()) return true;

		if (enumValues != null) {
			for (Object enumValue : enumValues) {
				if (value.equals(enumValue.toString())
					|| (this.annotation.ignoreCase() && value.equalsIgnoreCase(enumValue.toString()))) {
					return true;
				}
			}
		}

		return false;
	}

}
