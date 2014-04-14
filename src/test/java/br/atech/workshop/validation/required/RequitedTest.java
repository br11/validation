/**
 * 
 */
package br.atech.workshop.validation.required;

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
@SuppressWarnings("unchecked")
public class RequitedTest {

	@Test
	public void testManyViolations() {
		RequiredBean bean = new RequiredBean();

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,
				"br.atech.workshop.validation.required.RequiredBean.id",//
				"br.atech.workshop.validation.required.RequiredBean.birthDate",//
				"br.atech.workshop.validation.required.RequiredBean.name");
	}

	@Test
	public void testOneViolation() {
		RequiredBean bean = new RequiredBean();
		bean.setId(123);
		bean.setBirthDate(new Date());

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1,//
				"br.atech.workshop.validation.required.RequiredBean.name");
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

		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1,//
				"br.atech.workshop.validation.required.RequiredBean.name");
	}
}
