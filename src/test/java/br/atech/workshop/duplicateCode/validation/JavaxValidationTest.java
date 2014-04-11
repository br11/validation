package br.atech.workshop.duplicateCode.validation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

public class JavaxValidationTest {

	Validator validator = new SimpleValidator();

	@Test
	public void testDecimalMax() {
		JavaxValidationBean bean = new JavaxValidationBean();

		Set<ConstraintViolation<JavaxValidationBean>> validations;

		bean.setNumber1(new BigDecimal("123456789012345678901234567890"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber1(new BigDecimal("1234567890123456789012345678901"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testDecimalMin() {
		JavaxValidationBean bean = new JavaxValidationBean();

		Set<ConstraintViolation<JavaxValidationBean>> validations;

		bean.setNumber2(new BigDecimal("-12345678901234567890123456789"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber2(new BigDecimal("-123456789012345678901234567891"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testDigits() {
		JavaxValidationBean bean;
		
		Set<ConstraintViolation<JavaxValidationBean>> validations;

		// Double
		bean = new JavaxValidationBean();

		bean.setNumber3(12345.67);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber3(1234.5);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber3(123456.7);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber3(1234.567);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		// BigDecimal
		bean = new JavaxValidationBean();

		bean.setNumber4(new BigDecimal("12345.67"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber4(new BigDecimal("1234.5"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setNumber4(new BigDecimal("123456.7"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setNumber4(new BigDecimal("1234.567"));
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testNotNull() {
		JavaxValidationBean bean = new JavaxValidationBean();

		Set<ConstraintViolation<JavaxValidationBean>> validations;

		bean.setText1("");
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setText1(null);
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testPattern() {
		JavaxValidationBean bean = new JavaxValidationBean();

		Set<ConstraintViolation<JavaxValidationBean>> validations;

		bean.setText2("a");
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setText2(" a ");
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setText2("");
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());

		bean.setText2(" ");
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testPast() {
		JavaxValidationBean bean = new JavaxValidationBean();

		Set<ConstraintViolation<JavaxValidationBean>> validations;

		bean.setDate1(new Date(System.currentTimeMillis()-1));;
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setDate1(new Date(System.currentTimeMillis()+1));;
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

	@Test
	public void testFuture() {
		JavaxValidationBean bean = new JavaxValidationBean();

		Set<ConstraintViolation<JavaxValidationBean>> validations;

		bean.setDate2(new Date(System.currentTimeMillis()+100));;
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertTrue(validations.isEmpty());

		bean.setDate2(new Date(System.currentTimeMillis()-1));;
		validations = validator.validate(bean);
		System.out.println(validations);
		Assert.assertFalse(validations.isEmpty());
	}

}
