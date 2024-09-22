package ru.neostudy.calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neostudy.calculator.dto.VacationPaymentRequest;
import ru.neostudy.calculator.dto.VacationPaymentResponse;
import ru.neostudy.calculator.service.CalculatorService;
import ru.neostudy.calculator.validator.RequestTypeValidation;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Calculator Controller", description = "Controller has a GET/method for calculation")
public class CalculatorController {

    private final CalculatorService vacationPayCalculatorService;

    @Operation(
            summary = "Calculation"
    )
    @GetMapping("/calculate")
    public VacationPaymentResponse calculateVacationPayment(@Valid
                                                            @RequestTypeValidation
                                                            @ParameterObject
                                                            VacationPaymentRequest request) {
        return vacationPayCalculatorService.calculate(request);
    }
}
