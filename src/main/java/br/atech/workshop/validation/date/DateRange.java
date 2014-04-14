package br.atech.workshop.validation.date;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;

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
@Constraint(validatedBy = DateRangeValidator.class)
public @interface DateRange {

	/**
	 * 
	 */
	public enum Ranges {
		Any(), //

		Now(Calendar.MINUTE, 0, 0), //

		Past(Calendar.DAY_OF_MONTH, -365 * 2500, -1), //
		Future(Calendar.DAY_OF_MONTH, 1, +365 * 2500), //

		Today(Calendar.DAY_OF_MONTH, 0, 0), //
		Yesterday(Calendar.DAY_OF_MONTH, -1, -1), //
		Tomorrow(Calendar.DAY_OF_MONTH, +1, +1), //

		ThisWeek(Calendar.WEEK_OF_YEAR, 0, 0), //
		LastWeek(Calendar.WEEK_OF_YEAR, -1, -1), //
		NextWeek(Calendar.WEEK_OF_YEAR, +1, +1), //

		ThisMonth(Calendar.MONTH, 0, 0), //
		LastMonth(Calendar.MONTH, -1, -1), //
		NextMonth(Calendar.MONTH, +1, +1), //

		ThisYear(Calendar.YEAR, 0, 0), //
		LastYear(Calendar.YEAR, -1, -1), //
		NextYear(Calendar.YEAR, +1, +1), //

		FPL(Calendar.MINUTE, -45, 120 * 60); //

		private int predefGapField;
		private int predefGap1;
		private int predefGap2;
		private int gapField;

		private DateUtil util = new DateUtil();

		private Ranges() {

		}

		/**
		 * 
		 * @param predefGapField
		 * @param predefGap1
		 * @param predefGap2
		 */
		private Ranges(int predefGapField, int predefGap1, int predefGap2) {
			this(predefGapField, predefGap1, predefGap2, predefGapField);
		}

		/**
		 * 
		 * @param predefGapField
		 * @param predefGap1
		 * @param predefGap2
		 * @param gapField
		 */
		private Ranges(int predefGapField, int predefGap1, int predefGap2,
				int gapField) {
			this.predefGapField = predefGapField;
			this.predefGap1 = predefGap1;
			this.predefGap2 = predefGap2;
			this.gapField = gapField;
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValid(Date value, int minGap, int maxGap,
				ConstraintValidatorContext context) {
			if (value == null || this.equals(Any)) {
				return true;
			}

			return isValidMin(value, minGap, context)
					&& isValidMax(value, maxGap, context);
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValidMin(Date value, int gap,
				ConstraintValidatorContext context) {
			if (value == null || this.equals(Any)) {
				return true;
			}
			return value.compareTo(getMinDate(gap)) >= 0;
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValidMax(Date value, int gap,
				ConstraintValidatorContext context) {
			if (value == null || this.equals(Any)) {
				return true;
			}
			return value.compareTo(getMaxDate(gap)) <= 0;
		}

		/**
		 * 
		 * @return
		 */
		public Date getMinDate(int gap) {
			return util.floor(util.add(util.add(Calendar.getInstance()
					.getTime(), predefGapField, predefGap1), gapField, gap),
					predefGapField);
		}

		/**
		 * 
		 * @return
		 */
		public Date getMaxDate(int gap) {
			return util.ceil(util.add(util.add(
					Calendar.getInstance().getTime(), predefGapField,
					predefGap2), gapField, gap), predefGapField);
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			return getClass().getName() + "." + name();
		}

	}

	String message() default "O valor informado no campo {field} está fora dos limites permitidos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Ranges min() default Ranges.Past;

	Ranges max() default Ranges.Future;

	int minGap() default 0;

	int maxGap() default 0;

	Ranges[] value() default Ranges.Any;

	boolean todate() default false;
}
