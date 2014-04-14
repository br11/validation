package br.atech.workshop.validation.numeric;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author marcio
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
public @interface Numeric {

	public static final String CURRENCY = "#########.##";

	String message() default "{javax.validation.constraints.Digits.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();

	double min() default Double.MIN_VALUE;

	double max() default Double.MAX_VALUE;
}
