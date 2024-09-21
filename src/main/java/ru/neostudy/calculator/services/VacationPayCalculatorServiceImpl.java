package ru.neostudy.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neostudy.calculator.dto.VacationPayDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class VacationPayCalculatorServiceImpl implements CalculatorService {

    private final ProductionCalendarService productionCalendarService;
    private final double AVG_NUMBER_OF_DAYS = 29.3;
    private final double TAX_PERCENT = 0.13;

    @Autowired
    public VacationPayCalculatorServiceImpl(ProductionCalendarService productionCalendarService) {
        this.productionCalendarService = productionCalendarService;
    }

    @Override
    public VacationPayDto calculate(BigDecimal avgSalary, Long vacationDays, LocalDate beginDate, LocalDate endDate) {
        Long paidDays = 0L;

        if (vacationDays != null) {
            paidDays = vacationDays;
        }

        if (beginDate != null && endDate != null) {
            paidDays = productionCalendarService.getPaidVacationDays(beginDate, endDate);
        }

        BigDecimal payWithTax = getAvgDailyEarnings(avgSalary).multiply(BigDecimal.valueOf(paidDays));
        BigDecimal totalPay = payWithTax.subtract(percentOf(TAX_PERCENT, payWithTax));

        return VacationPayDto.builder()
                .message("The amount of vacation pay:")
                .totalPay(totalPay + " RUB")
                .build();
    }

    public BigDecimal getAvgDailyEarnings(BigDecimal avgSalary) {
        return avgSalary.divide(BigDecimal.valueOf(AVG_NUMBER_OF_DAYS), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal percentOf(double percentage, BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(percentage)).setScale(0, RoundingMode.HALF_UP);
    }

}
