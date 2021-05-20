package it.test.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PaymentTypeValidator.class)
@Documented
public @interface PaymentType {

    String message() default "Tipo pagamento non valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}