package ru.neostudy.calculator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neostudy.calculator.exception.response.ValidationErrorResponse;
import ru.neostudy.calculator.exception.response.Violation;

import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse beanPropertyBindingException(BindException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField().equals("beginDate") || error.getField().equals("endDate") ?
                        new Violation( "The date format is \"yyyy-MM-dd\"") : new Violation( error.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ValidationErrorResponse(violations);
    }
}
