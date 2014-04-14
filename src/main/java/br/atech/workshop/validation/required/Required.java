package br.atech.workshop.validation.required;

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
@Constraint(validatedBy = RequiredValidator.class)
public @interface Required {

	String message() default "{javax.validation.constraints.NotNull.message}"; 

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
