/**
 * 
 */
package br.atech.workshop.validation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Assert;

/**
 * @author marcio
 * 
 */
public class TestUtil {

	public static void assertValid(Set<ConstraintViolation<?>> validations,
			String... fields) {

		for (ConstraintViolation<?> constraintViolation : validations) {
			System.out.println(constraintViolation);
		}

		Assert.assertEquals("número de violações inesperado.",
				fields.length, validations.size());

		Set<String> messages = getFields(validations);
		for (String expectedMessage : fields) {
			Assert.assertTrue("Violação esperada não encontrada: "
					+ expectedMessage, messages.contains(expectedMessage));
		}
	}

	public static Set<String> getFields(
			Set<ConstraintViolation<?>> validations) {
		Set<String> messages = new LinkedHashSet<>();
		for (ConstraintViolation<?> constraintViolation : validations) {
			messages.add(constraintViolation.getPropertyPath().toString());
		}
		return messages;
	}

}
