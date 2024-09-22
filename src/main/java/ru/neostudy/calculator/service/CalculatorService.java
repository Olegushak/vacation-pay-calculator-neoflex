package ru.neostudy.calculator.service;

import ru.neostudy.calculator.dto.VacationPaymentRequest;
import ru.neostudy.calculator.dto.VacationPaymentResponse;

public interface CalculatorService {

    VacationPaymentResponse calculate(VacationPaymentRequest param);
}
