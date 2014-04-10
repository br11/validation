/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

/**
 * @author marcio
 * 
 */
@SuppressWarnings("unchecked")
public class RequitedTest {

	@Test
	public void testManyViolations() {
		RequiredBean bean = new RequiredBean();

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,
				"Campo ${br.atech.workshop.duplicateCode.validation.RequiredBean.id} é obrigatório.",//
				"Campo ${br.atech.workshop.duplicateCode.validation.RequiredBean.birthDate} é obrigatório.",//
				"Campo ${br.atech.workshop.duplicateCode.validation.RequiredBean.name} é obrigatório.");
	}

	@Test
	public void testOneViolation() {
		RequiredBean bean = new RequiredBean();
		bean.setId(123);
		bean.setBirthDate(new Date());

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,//
				"Campo ${br.atech.workshop.duplicateCode.validation.RequiredBean.name} é obrigatório.");
	}

	@Test
	public void testNoViolation() {
		RequiredBean bean = new RequiredBean();
		bean.setId(123);
		bean.setName("FULANO");
		bean.setBirthDate(new Date());

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);
	}

	@Test
	public void testEmptyString() {
		RequiredBean bean = new RequiredBean();
		bean.setId(123);
		bean.setName(" ");
		bean.setBirthDate(new Date());

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,//
				"Campo ${br.atech.workshop.duplicateCode.validation.RequiredBean.name} é obrigatório.");
	}
}
