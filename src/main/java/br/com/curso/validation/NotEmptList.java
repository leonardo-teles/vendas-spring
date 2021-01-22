package br.com.curso.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptListValidator.class)
public @interface NotEmptList {

	String message() default "A lista não pode estar vazia";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
}
