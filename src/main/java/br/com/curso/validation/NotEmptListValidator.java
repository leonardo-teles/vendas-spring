package br.com.curso.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptListValidator implements ConstraintValidator<NotEmptList, List<?>> {

	@Override
	public boolean isValid(List<?> list, ConstraintValidatorContext context) {
		return list != null && !list.isEmpty();
	}

}
