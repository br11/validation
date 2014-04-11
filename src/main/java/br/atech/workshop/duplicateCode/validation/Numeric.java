package br.atech.workshop.duplicateCode.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author marcio
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
public @interface Numeric {

	String message() default "O valor informado no campo ${field} não está no formato requerido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();
}
