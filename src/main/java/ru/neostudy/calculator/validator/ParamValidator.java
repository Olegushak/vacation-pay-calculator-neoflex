package ru.neostudy.calculator.validator;

import ru.neostudy.calculator.dto.VacationPaymentRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ParamValidator implements ConstraintValidator<RequestTypeValidation, VacationPaymentRequest> {
    @Override
    public boolean isValid(VacationPaymentRequest request, ConstraintValidatorContext context) {

        if (request.getVacationDays() == null && request.getBeginDate() == null && request.getEndDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Set up the number of vacation days or the exact date of leaving on vacation."
                    )
                    .addConstraintViolation();
            return false;
        }

        if (request.getVacationDays() == null && request.getBeginDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Set up the begin date of vacation.")
                    .addConstraintViolation();
            return false;
        }

        if ((request.getVacationDays() == null) && (request.getEndDate() == null)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Set up the end date of vacation.")
                    .addConstraintViolation();
            return false;
        }

        if (request.getVacationDays() != null
                && (request.getBeginDate() != null || request.getEndDate() != null)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Remove date parameters for generals calculation")
                    .addConstraintViolation();
            return false;
        }

        if (request.getBeginDate() != null && !request.getEndDate().isAfter(request.getBeginDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End of vacation date must be after beginning date")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
