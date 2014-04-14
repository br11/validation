/**
 * 
 */
package br.atech.workshop.validation.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import br.atech.workshop.validation.TestUtil;
import br.atech.workshop.validation.validator.SimpleValidator;

/**
 * @author marcio
 * 
 */
public class DateRangeTest {
	private DateUtil util = new DateUtil();

	@SuppressWarnings("unchecked")
	@Test
	public void testAny() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2000; days <= 2000; days += 100) {
			bean = new DateRangeBean();
			bean.setDate2(util.add(new Date(), Calendar.YEAR, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPast() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2001; days <= 1; days += 100) {
			bean = new DateRangeBean();
			bean.setDate1(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
		}

		bean = new DateRangeBean();
		bean.setDate1(new Date());
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date1");

		bean = new DateRangeBean();
		bean.setDate1(util.add(new Date(), Calendar.DAY_OF_MONTH, +1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date1");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFuture() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = 1; days <= 2001; days += 100) {
			bean = new DateRangeBean();
			bean.setDate3(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
		}

		bean = new DateRangeBean();
		bean.setDate3(new Date());
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date3");

		bean = new DateRangeBean();
		bean.setDate3(util.add(new Date(), Calendar.DAY_OF_MONTH, -1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date3");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testYesterday() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2002; days <= -2; days += 100) {
			bean = new DateRangeBean();
			bean.setDate4(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date4");
		}

		for (int days = 0; days <= 2000; days += 100) {
			bean = new DateRangeBean();
			bean.setDate4(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date4");
		}

		bean = new DateRangeBean();
		bean.setDate4(util.add(new Date(), Calendar.DAY_OF_MONTH, -1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testToday() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2001; days <= -1; days += 100) {
			bean = new DateRangeBean();
			bean.setDate5(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date5");
		}

		for (int days = 1; days <= 2001; days += 100) {
			bean = new DateRangeBean();
			bean.setDate5(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date5");
		}

		bean = new DateRangeBean();
		bean.setDate5(util.set(new Date(), Calendar.HOUR_OF_DAY, 0));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testTomorrow() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2000; days <= 0; days += 100) {
			bean = new DateRangeBean();
			bean.setDate6(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date6");
		}

		for (int days = 2; days <= 2002; days += 100) {
			bean = new DateRangeBean();
			bean.setDate6(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date6");
		}

		bean = new DateRangeBean();
		bean.setDate6(util.add(new Date(), Calendar.DAY_OF_MONTH, +1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testLastWeek() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2014; days <= -14; days += 100) {
			bean = new DateRangeBean();
			bean.setDate4(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date4");
		}

		for (int days = 0; days <= 2000; days += 100) {
			bean = new DateRangeBean();
			bean.setDate4(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date4");
		}

		bean = new DateRangeBean();
		bean.setDate7(util.add(new Date(), Calendar.WEEK_OF_YEAR, -1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);

		bean = new DateRangeBean();
		bean.setDate7(new Date());
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date7");

		bean = new DateRangeBean();
		bean.setDate7(util.add(new Date(), Calendar.WEEK_OF_YEAR, -2));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date7");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testThisWeek() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2007; days <= -7; days += 100) {
			bean = new DateRangeBean();
			bean.setDate8(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date8");
		}

		for (int days = 7; days <= 2007; days += 100) {
			bean = new DateRangeBean();
			bean.setDate8(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date8");
		}

		bean = new DateRangeBean();
		bean.setDate8(new Date());
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);

		bean = new DateRangeBean();
		bean.setDate8(util.add(new Date(), Calendar.WEEK_OF_YEAR, +1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date8");

		bean = new DateRangeBean();
		bean.setDate8(util.add(new Date(), Calendar.WEEK_OF_YEAR, -1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date8");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testNextWeek() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		for (int days = -2000; days <= 0; days += 100) {
			bean = new DateRangeBean();
			bean.setDate9(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date9");
		}

		for (int days = 14; days <= 2014; days += 100) {
			bean = new DateRangeBean();
			bean.setDate9(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date9");
		}

		bean = new DateRangeBean();
		bean.setDate9(util.add(new Date(), Calendar.WEEK_OF_YEAR, +1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);

		bean = new DateRangeBean();
		bean.setDate9(new Date());
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date9");

		bean = new DateRangeBean();
		bean.setDate9(util.add(new Date(), Calendar.WEEK_OF_YEAR, +2));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date9");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMinMax() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;

		int end = 365 - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

		for (int days = 0; days <= end; days += 1) {
			bean = new DateRangeBean();
			bean.setDate10(util.add(new Date(), Calendar.DAY_OF_MONTH, days));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
		}

		bean = new DateRangeBean();
		bean.setDate10(util.add(new Date(), Calendar.DAY_OF_MONTH, -1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date10");

		bean = new DateRangeBean();
		bean.setDate10(util.add(new Date(), Calendar.DAY_OF_MONTH, end + 1));
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.date.DateRangeBean.date10");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFlightPlanDate() {
		SimpleValidator simpleValidator = new SimpleValidator();
		DateRangeBean bean;
		Set<?> validations;
		for (int minutes = -45; minutes <= (120 * 60); minutes += 5) {
			bean = new DateRangeBean();
			bean.setDate11(util.add(new Date(), Calendar.MINUTE, minutes));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
		}

		for (int minutes = -60; minutes < -45; minutes += 1) {
			bean = new DateRangeBean();
			bean.setDate11(util.add(new Date(), Calendar.MINUTE, minutes));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date11");
		}

		for (int minutes = (120 * 60 + 60); minutes < (120 * 60 + 120); minutes += 1) {
			System.out.println(minutes); 
			bean = new DateRangeBean();
			bean.setDate11(util.add(new Date(), Calendar.MINUTE, minutes));
			validations = simpleValidator.validate(bean);
			TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
					"br.atech.workshop.validation.date.DateRangeBean.date11");
		}

	}

}
