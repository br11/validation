package br.atech.workshop.duplicateCode.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author marcio
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateDomainValidator.class)
public @interface DateDomain {

	/**
	 * 
	 */
	public enum PreDef {
		Today(Calendar.DAY_OF_MONTH, 0, 0), //
		Yesterday(Calendar.DAY_OF_MONTH, -1, -1), //
		Tomorrow(Calendar.DAY_OF_MONTH, +1, +1), //
		ThisWeek(Calendar.WEEK_OF_YEAR, 0, 0), //
		LastWeek(Calendar.WEEK_OF_YEAR, -1, -1), //
		NextWeek(Calendar.WEEK_OF_YEAR, 0, 0), //
		ThisMonth(Calendar.MONTH, 0, 0), //
		LastMonth(Calendar.MONTH, -1, -1), //
		NextMonth(Calendar.MONTH, 0, 0), //
		ThisYear(Calendar.YEAR, 0, 0), //
		LastYear(Calendar.YEAR, -1, -1), //
		NextYear(Calendar.YEAR, 0, 0), //
		Past(Calendar.DAY_OF_MONTH, -999999, -1), //
		Future(Calendar.DAY_OF_MONTH, 1, +999999), //
		Any(Calendar.DAY_OF_MONTH, -999999, +999999), //
		NotNull(Calendar.DAY_OF_MONTH, 0, 0);

		int calField;
		int predefGap1;
		int predefGap2;

		/**
		 * 
		 * @param calField
		 * @param predefGap1
		 * @param predefGap2
		 */
		private PreDef(int calField, int predefGap1, int predefGap2) {
			this.calField = calField;
			this.predefGap1 = predefGap1;
			this.predefGap2 = predefGap2;
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValid(Date value, int minGap, int maxGap) {
			if (this.equals(NotNull)) {
				return value != null;
			}

			if (value == null) {
				return true;
			}

			return value.after(getMinDate(minGap))
					&& value.before(getMinDate(maxGap));
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValidMin(Date value, int gap) {
			if (this.equals(NotNull)) {
				return value != null;
			}

			if (value == null) {
				return true;
			}

			return value.after(getMinDate(gap));
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValidMax(Date value, int gap) {
			if (this.equals(NotNull)) {
				return value != null;
			}

			if (value == null) {
				return true;
			}

			return value.before(getMinDate(gap));
		}

		/**
		 * 
		 * @return
		 */
		public Date getMinDate(int gap) {
			return add(
					add(floor(Calendar.getInstance().getTime(), calField),
							calField, predefGap1), calField, gap);
		}

		/**
		 * 
		 * @return
		 */
		public Date getMaxDate(int gap) {
			return add(
					add(ceil(Calendar.getInstance().getTime(), calField),
							calField, predefGap2), calField, predefGap2);
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			return getClass().getName() + "." + name();
		}

		/**
		 * 
		 * @param date
		 * @param field
		 * @return
		 */
		public Date ceil(Date date, int field) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(field, cal.getMaximum(field));
			cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
			cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
			cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
			cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
			return cal.getTime();
		}

		/**
		 * 
		 * @param date
		 * @param field
		 * @return
		 */
		public Date floor(Date date, int field) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(field, cal.getMinimum(field));
			cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
			cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
			cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
			cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
			return cal.getTime();
		}

		/**
		 * 
		 * @param date
		 * @param field
		 * @param amount
		 * @return
		 */
		public Date add(Date date, int field, int amount) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(field, amount);
			return cal.getTime();
		}
	}

	String message() default "O valor informado no campo {field} está fora dos limites permitidos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	PreDef min() default PreDef.Past;

	PreDef max() default PreDef.Future;

	int minGap() default 0;

	int maxGap() default 0;

	PreDef[] value() default PreDef.Any;
}
