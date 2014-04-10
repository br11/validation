package br.atech.workshop.duplicateCode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.duplicateCode.validation.NumericRange.PreDef;

/**
 * 
 * @author marcio
 * 
 */
public class NumericRangeValidator implements
		ConstraintValidator<NumericRange, Object> {

	private RequiredValidator requiredValidator = new RequiredValidator();

	private NumericRange annotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(NumericRange annotation) {
		this.requiredValidator.initialize(null);
		this.annotation = annotation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		for (PreDef predef : annotation.value()) {
			if (!predef.isValid((Number) value, context)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						predef.getErrorMessage()).addConstraintViolation();
				return false;
			}
		}

		if (!new RequiredValidator().isValid(value, context)) {
			return true;
		}

		return ((Number) value).doubleValue() >= annotation.min()
				&& ((Number) value).doubleValue() <= annotation.max();
	}
}
