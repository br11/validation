package br.atech.workshop.duplicateCode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.duplicateCode.validation.NumericDomain.PreDef;

/**
 * 
 * @author marcio
 * 
 */
public class NumericDomainValidator implements
		ConstraintValidator<NumericDomain, Object> {

	private RequiredValidator requiredValidator = new RequiredValidator();

	private NumericDomain annotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(NumericDomain annotation) {
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
