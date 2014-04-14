package br.atech.workshop.validation.numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.validation.required.RequiredValidator;

public class NumericValidator implements ConstraintValidator<Numeric, Object> {

	private RequiredValidator requiredValidator = new RequiredValidator();

	private int maxInteger;
	private int maxFraction;

	private BigDecimal min;
	private BigDecimal max;

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

		String[] parts = annotation.value().split("\\.");

		String integerPart = parts[0];
		maxInteger = integerPart.length();

		String fractionPart = parts.length == 2 ? parts[1] : "";
		maxFraction = fractionPart.length();

		min = new BigDecimal(annotation.min());

		max = new BigDecimal(annotation.max()).min(new BigDecimal(Math
				.round(Math.pow(10, maxInteger)) - 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (!requiredValidator.isValid(value, context)) {
			return true;
		}

		BigDecimal big = toBig(value);

		if (big.compareTo(min) < 0 && big.compareTo(max) > 0) {
			buildConstraintViolation(context,
					"{br.atech.workshop.validation.Range.message}");
			return false;
		} else if (big.compareTo(min) < 0) {
			buildConstraintViolation(context,
					"{javax.validation.constraints.Min.message}");
			return false;
		} else if (big.compareTo(max) > 0) {
			buildConstraintViolation(context,
					"{javax.validation.constraints.Max.message}");
			return false;
		} else if (!verifyInteger(value, maxInteger)
				|| !verifyFraction(value, maxFraction)) {
			return false;
		}

		return true;
	}

	private void buildConstraintViolation(ConstraintValidatorContext context,
			String message) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation();
	}

	/**
	 * 
	 * @param value
	 * @param integer
	 * @return
	 */
	boolean verifyInteger(Object value, int integer) {
		BigDecimal big = toBig(value);
		int precision = getPrecision(value);

		big = big.round(new MathContext(precision, RoundingMode.HALF_UP));
		BigDecimal multi = new BigDecimal("10").pow(integer, new MathContext(
				precision, RoundingMode.HALF_UP));
		return big.compareTo(multi) < 0;
	}

	/**
	 * 
	 * @param value
	 * @param fraction
	 * @return
	 */
	boolean verifyFraction(Object value, int fraction) {
		BigDecimal big = toBig(value);
		int precision = getPrecision(value);

		try {
			big = big.round(new MathContext(precision, RoundingMode.HALF_UP));
			int multi = (int) Math.round(Math.pow(10, fraction));
			big = big.multiply(new BigDecimal(multi));
			big.longValueExact();
		} catch (ArithmeticException e) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private BigDecimal toBig(Object value) {
		BigDecimal big;
		if (value instanceof BigDecimal) {
			big = (BigDecimal) value;
		} else if (value instanceof BigInteger) {
			big = new BigDecimal((BigInteger) value);
		} else if (value instanceof Double) {
			big = new BigDecimal(((Number) value).doubleValue());
		} else if (value instanceof Float) {
			big = new BigDecimal(((Number) value).floatValue());
		} else if (value instanceof Number) {
			big = new BigDecimal(((Number) value).longValue());
		} else {
			big = new BigDecimal(value.toString());
		}
		return big;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	int getPrecision(Object value) {
		if (value instanceof Float) {
			return 7;
		} else {
			return 15;
		}
	}

}
