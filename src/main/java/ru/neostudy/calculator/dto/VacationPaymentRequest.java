package ru.neostudy.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VacationPaymentRequest {

    @NotNull(message = "Set up the average salary")
    @DecimalMin(value = "0.0", inclusive = false, message = "The average salary must be greater than 0.0")
    private BigDecimal avgSalary;

    @Min(value = 1, message = "The amount of vacation days must be greater than 0")
    @Max(value = 80, message = "The amount of vacation days must be less than 80")
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer vacationDays;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beginDate;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

}
