package ru.neostudy.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class VacationPaymentRequest {

    @NotNull(message = "Set up the average salary")
    @DecimalMin(value = "0.0", inclusive = false, message = "The average salary must be greater than 0.0")
    private BigDecimal avgSalary;

    @Min(value = 1, message = "The amount of vacation days must be greater than 0")
    @Max(value = 80, message = "The amount of vacation days must be less than 80")
    private Integer vacationDays;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beginDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;


}
