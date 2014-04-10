package br.atech.workshop.duplicateCode.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * 
 * @author marcio
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DomainValidator.class)
public @interface Domain {

	/**
	 * 
	 */
	public enum PreDef {
		Any(".*", "Valor informado no campo ${field} é inválido."), //
		Alfa("[a-zA-Z]*", "Campo ${field} deve conter apenas letras."), //
		Name("[\\. a-zA-Z]*", "Campo ${field} deve conter um nome válido."), //
		Text("[\\.,;:\\(\\)\\-_ a-zA-Z0-9]*", "Campo ${field} não deve conter caracteres especiais."), //
		Alfanum("[a-zA-Z0-9]*",
				"Campo ${field} deve conter apenas letras e números."), //
		NotNull(".+", "Campo ${field} é obrigatório.");

		private final String regex;
		private final String errorMessage;

		/**
		 * 
		 * @param regex
		 * @param errorMessage
		 */
		private PreDef(String regex, String errorMessage) {
			this.regex = regex;
			this.errorMessage = errorMessage;
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			
			if (!new RequiredValidator().isValid(value, context)) {
				if (this.equals(NotNull)) {
					
					return false;
				} else {
					return true;
				}
			}

			return value.toString().matches(regex);
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() { 
			return errorMessage;
		}
	}

	String message() default "O valor informado no campo ${field} está fora dos limites permitidos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	PreDef[] value() default PreDef.Any;
}
