package com.mission.todolist.domain.Item.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
public @interface EnumTypeValid {

	String message() default "invalid parameter!!";
	Class<?>[] groups() default {};
	Class<? extends java.lang.Enum<?>> target();
	Class<? extends Payload>[] payload() default{};
	boolean ignoreCase() default false;
	boolean isNull() default false;

}


