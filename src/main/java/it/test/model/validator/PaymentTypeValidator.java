package it.test.model.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.ObjectUtils;

public class PaymentTypeValidator implements ConstraintValidator<PaymentType, String> {
	
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	context.disableDefaultConstraintViolation();
    	context.buildConstraintViolationWithTemplate("Tipo pagamento non valido. Valori accettati " + Arrays.asList(PaymentTypes.values()))
    			.addConstraintViolation();
    	
    	return ObjectUtils.containsConstant(PaymentTypes.values(), value);
    }
}