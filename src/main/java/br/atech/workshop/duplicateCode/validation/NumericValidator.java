package br.atech.workshop.duplicateCode.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<Numeric, Object> {

	private RequiredValidator requiredValidator = new RequiredValidator();

	private Pattern zeroPattern = Pattern.compile("(0+)");

	private int maxInteger;
	private int minInteger;

	private int maxFraction;
	private int minFraction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Numeric annotation) {
		this.requiredValidator.initialize(null);

		Matcher zeroMatcher;

		String[] parts = annotation.value().split("\\.");

		String integerPart = parts[0];
		maxInteger = integerPart.length();
		minInteger = 0;
		zeroMatcher = zeroPattern.matcher(integerPart);
		if (zeroMatcher.find()) {
			minInteger = zeroMatcher.group(1).length();
		}

		String fractionPart = parts.length == 2 ? parts[1] : "";
		maxFraction = fractionPart.length();
		minFraction = 0;
		zeroMatcher = zeroPattern.matcher(fractionPart);
		if (zeroMatcher.find()) {
			minFraction = zeroMatcher.group(1).length();
		}
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

		String[] parts = value.toString().split("\\.");
		String integerPart = parts[0];
		String fractionPart = parts.length == 2 ? parts[1] : "";

		int integer = integerPart.length();
		int fraction = !fractionPart.matches("0+") ? fractionPart.length() : 0;

		return (integer >= minInteger && integer <= maxInteger)
				&& (fraction >= minFraction && fraction <= maxFraction);
	}
}
