package br.atech.workshop.validation.required;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequiredValidator implements ConstraintValidator<Required, Object> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Required annotation) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		} else if (value instanceof String) {
			return !((String) value).trim().isEmpty();
		} else if (value instanceof Collection<?>) {
			return !((Collection<?>) value).isEmpty();
		} else if (value.getClass().isArray()) {
			return ((Object[]) value).length > 0;
		} else {
			return true;
		}
	}

}
