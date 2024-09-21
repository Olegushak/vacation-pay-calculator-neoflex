package ru.neostudy.calculator.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VacationPayDto {
    private String message;
    private String totalPay;
}
