package br.atech.workshop.duplicateCode.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.duplicateCode.validation.DateRange.PreDef;

/**
 * 
 * @author marcio
 * 
 */
public class DateRangeValidator implements
		ConstraintValidator<DateRange, Object> {

	private DateRange annotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(DateRange annotation) {
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
		if (!new RequiredValidator().isValid(value, context)) {
			return true;
		}
		
		Date dateValue = (Date) value;

		for (PreDef predef : annotation.value()) {
			if (!predef.isValid(dateValue, annotation.minGap(),
					annotation.maxGap())) {
				buildConstraintViolation(context, predef);
				return false;
			}
		}
		if (!annotation.min().isValidMin(dateValue, annotation.minGap(),
				context)) {
			buildConstraintViolation(context, annotation.min());
			return false;
		}
		if (!annotation.max().isValidMax(dateValue, annotation.maxGap(),
				context)) {
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
