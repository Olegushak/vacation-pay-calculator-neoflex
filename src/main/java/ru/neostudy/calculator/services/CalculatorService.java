package ru.neostudy.calculator.services;

import ru.neostudy.calculator.dto.VacationPayDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculatorService {

    VacationPayDto calculate(BigDecimal avgSalary, Long vacationDays, LocalDate beginDate, LocalDate endDate);
}
