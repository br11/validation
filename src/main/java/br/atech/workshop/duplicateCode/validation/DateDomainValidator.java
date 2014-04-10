package br.atech.workshop.duplicateCode.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.duplicateCode.validation.DateDomain.PreDef;

/**
 * 
 * @author marcio
 * 
 */
public class DateDomainValidator implements
		ConstraintValidator<DateDomain, Object> {

	private DateDomain annotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(DateDomain annotation) {
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
		if (value == null || value.toString().isEmpty()) {
			return true;
		}

		for (PreDef predef : annotation.value()) {
			if (!predef.isValid((Date) value, annotation.minGap(),
					annotation.maxGap())) {
				buildConstraintViolation(context, predef);
				return false;
			}
		}
		if (!annotation.min().isValidMin((Date) value, annotation.minGap())) {
			buildConstraintViolation(context, annotation.min());
			return false;
		}
		if (!annotation.max().isValidMax((Date) value, annotation.maxGap())) {
			buildConstraintViolation(context, annotation.max());
			return false;
		}

		return true;
	}

	private void buildConstraintViolation(ConstraintValidatorContext context,
			PreDef predef) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(predef.getErrorMessage())
				.addConstraintViolation();
	}
}
