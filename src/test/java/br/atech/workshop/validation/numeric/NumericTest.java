/**
 * 
 */
package br.atech.workshop.validation.numeric;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Assert;
import org.junit.Test;

import br.atech.workshop.validation.TestUtil;
import br.atech.workshop.validation.validator.SimpleValidator;

/**
 * @author marcio
 * 
 */
public class NumericTest {

	NumericValidator validator = new NumericValidator();

	@Test
	public void testDoubleFraction() {
		Assert.assertTrue(validator.verifyFraction(24.4, 1));
		Assert.assertFalse(validator.verifyFraction(24.4, 0));
		Assert.assertTrue(validator.verifyFraction(24.45678, 5));
		Assert.assertFalse(validator.verifyFraction(24.45678, 4));

	}

	@Test
	public void testFloatFraction() {
		Assert.assertTrue(validator.verifyFraction(24.4f, 1));
		Assert.assertFalse(validator.verifyFraction(24.4f, 0));
		Assert.assertTrue(validator.verifyFraction(24.45678f, 5));
		Assert.assertFalse(validator.verifyFraction(24.45678f, 4));
	}

	@Test
	public void testIntegerFraction() {
		Assert.assertTrue(validator.verifyFraction(24, 1));
		Assert.assertTrue(validator.verifyFraction(24, 0));
		Assert.assertTrue(validator.verifyFraction(24, 5));
		Assert.assertTrue(validator.verifyFraction(24, 4));
	}

	@Test
	public void testLongFraction() {
		Assert.assertTrue(validator.verifyFraction(24l, 1));
		Assert.assertTrue(validator.verifyFraction(24l, 0));
		Assert.assertTrue(validator.verifyFraction(24l, 5));
		Assert.assertTrue(validator.verifyFraction(24l, 4));
	}

	@Test
	public void testStringFraction() {
		Assert.assertTrue(validator.verifyFraction("24.4", 1));
		Assert.assertFalse(validator.verifyFraction("24.4", 0));
		Assert.assertTrue(validator.verifyFraction("24.45678", 5));
		Assert.assertFalse(validator.verifyFraction("24.45678", 4));
	}

	@Test
	public void testDoubleInteger() {
		Assert.assertTrue(validator.verifyInteger(24.4, 2));
		Assert.assertFalse(validator.verifyInteger(24.4, 1));
		Assert.assertTrue(validator.verifyInteger(24567.45678, 5));
		Assert.assertFalse(validator.verifyInteger(24567.45678, 4));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMinMax() {
		SimpleValidator simpleValidator = new SimpleValidator();
		NumericBean bean;
		Set<?> validations;

		bean = new NumericBean();
		bean.setNumber1(9);
		bean.setNumber2(99);
		bean.setNumber3(999);
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);

		bean = new NumericBean();
		bean.setNumber1(10);
		bean.setNumber2(100);
		bean.setNumber3(1000);
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.numeric.NumericBean.number1",
				"br.atech.workshop.validation.numeric.NumericBean.number2",
				"br.atech.workshop.validation.numeric.NumericBean.number3");

		bean = new NumericBean();
		bean.setNumber7(99.0);
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.numeric.NumericBean.number7");

		bean = new NumericBean();
		bean.setNumber7(98.0);
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAnnotation() {
		SimpleValidator simpleValidator = new SimpleValidator();
		NumericBean bean;
		Set<?> validations;

		bean = new NumericBean();
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);

		bean = new NumericBean();
		bean.setNumber1(1);
		bean.setNumber2(12);
		bean.setNumber3(123);
		bean.setNumber4(123.0);
		bean.setNumber5(123.4);
		bean.setNumber6(123.45);
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations);

		bean = new NumericBean();
		bean.setNumber1(12);
		bean.setNumber2(123);
		bean.setNumber3(1234);
		bean.setNumber4(123.01);
		bean.setNumber5(123.41);
		bean.setNumber6(123.451);
		validations = simpleValidator.validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validations,
				"br.atech.workshop.validation.numeric.NumericBean.number1",
				"br.atech.workshop.validation.numeric.NumericBean.number2",
				"br.atech.workshop.validation.numeric.NumericBean.number3",
				"br.atech.workshop.validation.numeric.NumericBean.number4",
				"br.atech.workshop.validation.numeric.NumericBean.number5",
				"br.atech.workshop.validation.numeric.NumericBean.number6");
	}
}
