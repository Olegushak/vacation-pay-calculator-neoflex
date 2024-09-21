package ru.neostudy.calculator.utils.validator.anotations;

import ru.neostudy.calculator.utils.validator.ParamValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ParamValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidParameter {

    String message() default "Неверные параметры";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
