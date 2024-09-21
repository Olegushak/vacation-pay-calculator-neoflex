package ru.neostudy.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neostudy.calculator.dto.RequestParametersDto;
import ru.neostudy.calculator.dto.VacationPayDto;
import ru.neostudy.calculator.services.CalculatorService;
import ru.neostudy.calculator.utils.validator.anotations.ValidParameter;

@Validated
@RestController
public class CalculatorController {
    private final CalculatorService vacationPayCalculatorService;

    @Autowired
    public CalculatorController(CalculatorService vacationPayCalculatorService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
    }

    @GetMapping("/calculate")
    public VacationPayDto getVacationPay(@ValidParameter RequestParametersDto parameters) {
        return vacationPayCalculatorService.calculate(parameters.getAvgSalary(), parameters.getVacationDays(), parameters.getBeginDate(), parameters.getEndDate());
    }
}
