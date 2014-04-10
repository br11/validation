package br.atech.workshop.duplicateCode.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * 
 * @author marcio
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumericRangeValidator.class)
public @interface NumericRange {

	/**
	 * 
	 */
	public enum PreDef {
		Small(-1000, +1000), //
		Medium(-10000, +10000), //
		Big(-1000000, +1000000), //
		Age(0, 200), //
		Salary(780.00, 780.99);

		private final double predefMin;
		private final double predefMax;
		private String message;

		private PreDef(double predefMin, double predefMax) {
			this(predefMin, predefMax, null);
		}

		private PreDef(double predefMin, double predefMax, String message) {
			this.predefMin = predefMin;
			this.predefMax = predefMax;
			this.message = message;
		}

		/**
		 * 
		 * @param value
		 * @param context
		 * @return
		 */
		public boolean isValid(Number value, ConstraintValidatorContext context) {
			boolean valid = value.doubleValue() >= predefMin
					&& value.doubleValue() <= predefMax;

			return valid;
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			if (message == null) {
				message = String.format("Campo ${field} deve estar entre %s e %s.", predefMin, predefMax);
			}
			return message;
		}
	}

	String message() default "O valor informado no campo {field} está fora dos limites permitidos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int min() default Integer.MIN_VALUE;

	int max() default Integer.MAX_VALUE;

	PreDef[] value() default PreDef.Big;

}
