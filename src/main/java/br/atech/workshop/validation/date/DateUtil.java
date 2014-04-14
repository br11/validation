package br.atech.workshop.validation.date;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	int[] fields = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
			Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND,
			Calendar.MILLISECOND };

	/**
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public Date ceil(Date date, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for (int currentField : fields) {
			if (currentField > field) {
				if (currentField == Calendar.DAY_OF_MONTH
						&& (field == Calendar.WEEK_OF_MONTH || field == Calendar.WEEK_OF_YEAR)) {
					continue;
				}
				cal.set(currentField, cal.getActualMaximum(currentField));
			}
		}
		if (field == Calendar.WEEK_OF_MONTH || field == Calendar.WEEK_OF_YEAR) {
			if (cal.getFirstDayOfWeek() == Calendar.MONDAY) {
				cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				cal.add(Calendar.DAY_OF_WEEK, 6);
			}
		}
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
		for (int currentField : fields) {
			if (currentField > field) {
				if (currentField == Calendar.DAY_OF_MONTH
						&& (field == Calendar.WEEK_OF_MONTH || field == Calendar.WEEK_OF_YEAR)) {
					continue;
				}
				cal.set(currentField, cal.getActualMinimum(currentField));
			}
		}
		if (field == Calendar.WEEK_OF_MONTH || field == Calendar.WEEK_OF_YEAR) {
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		}
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

	/**
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public Date set(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(field, amount);

		// System.out.println("set " + date + " > " + cal.getTime());

		return cal.getTime();
	}
}
