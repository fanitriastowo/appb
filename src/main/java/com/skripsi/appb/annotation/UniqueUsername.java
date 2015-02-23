package com.skripsi.apsb.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom Annotation for Unique Username
 * <p/>
 * 
 * @author anonymous
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidator.class })
public @interface UniqueUsername {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
