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
public class DomainTest {

	@Test
	public void testManyViolations() {
		DomainBean bean = new DomainBean();

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.id} é obrigatório.",//
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.name} é obrigatório.");
	}

	@Test
	public void testEmptyString() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setName(" ");
		bean.setBirthDate(new Date());

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,//
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.name} é obrigatório.");
	}

	@Test
	public void testAny() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setName("FULANO");
		bean.setBirthDate(new Date());

		bean.setText3("$%$%<>:?}`{ {{}+-=-!@#$%-*/.");

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);
	}

	@Test
	public void testAlfa() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setName("FULANO");
		bean.setBirthDate(new Date());

		bean.setText1("abcABC");
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setText1("abcABC123*()");
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.text1} deve conter apenas letras.");
	}

	@Test
	public void testName() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setBirthDate(new Date());

		bean.setName("FULANO");
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setName("FULANO1;");
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.name} deve conter um nome válido.");
	}

	@Test
	public void testText() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setName("FULANO");
		bean.setBirthDate(new Date());

		bean.setText5("um texto de teste.;():");
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setText5("&@%&#*");
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.text5} não deve conter caracteres especiais.");
	}

	@Test
	public void testAlfaNum() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setName("FULANO");
		bean.setBirthDate(new Date());

		bean.setText2("abc123");
		Set<?> validate1 = new SimpleValidator().validate(bean);
		TestUtil.assertValid((Set<ConstraintViolation<?>>) validate1);

		bean.setText2("abc 123");
		Set<?> validate2 = new SimpleValidator().validate(bean);
		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate2, //
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.text2} deve conter apenas letras e números.");
	}

	@Test
	public void testNotNull() {
		DomainBean bean = new DomainBean();
		bean.setId(123);
		bean.setBirthDate(new Date());

		Set<?> validate1 = new SimpleValidator().validate(bean);

		TestUtil.assertValid(
				(Set<ConstraintViolation<?>>) validate1,//
				"Campo ${br.atech.workshop.duplicateCode.validation.DomainBean.name} é obrigatório.");
	}

}
