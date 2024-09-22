package ru.neostudy.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neostudy.calculator.config.properties.CalculatorProperties;
import ru.neostudy.calculator.dto.VacationPaymentResponse;
import ru.neostudy.calculator.dto.VacationPaymentRequest;
import ru.neostudy.calculator.utils.ProductionCalendar;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class VacationPayCalculatorServiceImpl implements CalculatorService {

    private final ProductionCalendar productionCalendarService;

    private final CalculatorProperties properties;

    @Override
    public VacationPaymentResponse calculate(VacationPaymentRequest request) {

        long paidDays = 0;

        if (request.getVacationDays() != null) {
            paidDays = request.getVacationDays();
        }

        if (request.getBeginDate()!= null && request.getEndDate() != null) {
            paidDays = productionCalendarService.getPaidVacationDays(request.getBeginDate(),request.getEndDate());
        }

        BigDecimal paymentWithTax = getAvgDailyEarnings(request.getAvgSalary()).multiply(BigDecimal.valueOf(paidDays));
        BigDecimal noTaxPayment = paymentWithTax.subtract(percentOf(properties.getTaxPercent(), paymentWithTax));

        return new VacationPaymentResponse(noTaxPayment.toString());
    }

    public BigDecimal getAvgDailyEarnings(BigDecimal avgSalary) {
        return avgSalary.divide(BigDecimal.valueOf(properties.getNumberOfDays()), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal percentOf(double percentage, BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(percentage)).setScale(0, RoundingMode.HALF_UP);
    }

}
