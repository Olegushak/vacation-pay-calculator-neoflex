package ru.neostudy.calculator.service;

import ru.neostudy.calculator.dto.VacationPaymentResponse;
import ru.neostudy.calculator.dto.VacationPaymentRequest;

public interface CalculatorService {

    VacationPaymentResponse calculate(VacationPaymentRequest param);
}
