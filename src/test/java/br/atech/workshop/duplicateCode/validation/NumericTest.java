/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author marcio
 * 
 */
public class NumericTest {

	Validator validator = new SimpleValidator();

	@Test
	public void testIntegerDigits() {
		NumericBean bean;

		Set<ConstraintViolation<NumericBean>> validations;

		//
		bean = new NumericBean();

		bean.setNumber1(123);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber1(12);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber1(1234);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		//
		bean = new NumericBean();

		bean.setNumber2(123);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber2(12);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber2(1);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber2(1234);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testDecimalDigitsNoFraction() {
		NumericBean bean;

		Set<ConstraintViolation<NumericBean>> validations;

		//
		bean = new NumericBean();

		bean.setNumber4(123.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber4(12.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber4(1.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber4(1234.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber4(123.4);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber4(12.3);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber4(1.23);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testDecimalDigits() {
		NumericBean bean;

		Set<ConstraintViolation<NumericBean>> validations;

		//
		bean = new NumericBean();

		bean.setNumber5(123.45);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber5(12.34);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber5(1.23);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber5(1.2);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber5(1.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber5(1234.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber5(1.234);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber5(1.0);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

	}

}
