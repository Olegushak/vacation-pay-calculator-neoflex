package ru.neostudy.calculator.utils.validator;

import org.springframework.stereotype.Component;
import ru.neostudy.calculator.dto.RequestParametersDto;
import ru.neostudy.calculator.utils.validator.anotations.ValidParameter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ParamValidator implements ConstraintValidator<ValidParameter, RequestParametersDto> {
    @Override
    public boolean isValid(RequestParametersDto parametersDto, ConstraintValidatorContext context) {
        if (parametersDto.getVacationDays() == null && parametersDto.getBeginDate() == null && parametersDto.getEndDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Set up the number of vacation days or the exact date of leaving on vacation.")
                    .addPropertyNode("selection of calculation type")
                    .addConstraintViolation();
            return false;
        }

        if (parametersDto.getVacationDays() == null && parametersDto.getBeginDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Set up the begin date of vacation.")
                    .addPropertyNode("beginDate")
                    .addConstraintViolation();
            return false;
        }

        if (parametersDto.getVacationDays() == null && parametersDto.getEndDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Set up the end date of vacation.")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            return false;
        }

        if (parametersDto.getVacationDays() != null && (parametersDto.getBeginDate() != null || parametersDto.getEndDate() != null)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Remove date parameters for generals calculation")
                    .addPropertyNode("selection of general calculation")
                    .addConstraintViolation();
            return false;
        }

        if (parametersDto.getBeginDate() != null && !parametersDto.getEndDate().isAfter(parametersDto.getBeginDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End of vacation date must be after beginning date")
                    .addPropertyNode("endDate is after beginDate")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
