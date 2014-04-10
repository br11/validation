package br.atech.workshop.duplicateCode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.duplicateCode.validation.Domain.PreDef;

public class DomainValidator implements ConstraintValidator<Domain, Object> {

	private RequiredValidator requiredValidator = new RequiredValidator();

	private Domain annotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Domain annotation) {
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
			if (!predef.isValid(value, context)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						predef.getErrorMessage()).addConstraintViolation();
				return false;
			}
		}
		return true;
	}
}
