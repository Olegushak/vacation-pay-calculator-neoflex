package ru.neostudy.calculator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ParamValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestTypeValidation {

    String message() default "Неверные параметры";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
