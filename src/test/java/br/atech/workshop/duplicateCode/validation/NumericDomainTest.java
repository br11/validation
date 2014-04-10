/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

/**
 * @author marcio
 * 
 */
@SuppressWarnings("unchecked")
public class NumericDomainTest {

	@Test
	public void testAge() {
		NumericDomainBean bean = new NumericDomainBean();
		bean.setId(123);

		bean.setAge(3);
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setAge(300);
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.NumericDomainBean.age} deve estar entre 0.0 e 200.0.");
	}

	@Test
	public void testSalary() {
		NumericDomainBean bean = new NumericDomainBean();
		bean.setId(123);

		bean.setSalary(780.00f);
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setSalary(781.00f);
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.NumericDomainBean.salary} deve estar entre 780.0 e 780.99.");
	}


	@Test
	public void testBig() {
		NumericDomainBean bean = new NumericDomainBean();
		bean.setId(123);

		bean.setOvertime(0.0);
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setOvertime(-12300000.0);
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.NumericDomainBean.overtime} deve estar entre -1000000.0 e 1000000.0.");
	}


	@Test
	public void testNotNull() {
		NumericDomainBean bean = new NumericDomainBean();

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,//
				"Campo ${br.atech.workshop.duplicateCode.validation.NumericDomainBean.id} é obrigatório.");
	}

}
