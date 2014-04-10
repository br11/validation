/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

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
			String... expectedMessages) {

		Assert.assertEquals("número de violações inesperado.",
				expectedMessages.length, validations.size());

		Set<String> messages = getMessages(validations);
		for (String expectedMessage : expectedMessages) {
			Assert.assertTrue("Violação esperada não encontrada: "
					+ expectedMessage, messages.contains(expectedMessage));
		}
	}

	public static Set<String> getMessages(
			Set<ConstraintViolation<?>> validations) {
		Set<String> messages = new LinkedHashSet<>();
		for (ConstraintViolation<?> constraintViolation : validations) {
			System.out.println(constraintViolation);

			messages.add(constraintViolation.getMessage());
		}
		return messages;
	}

}
