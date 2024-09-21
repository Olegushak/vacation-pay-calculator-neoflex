package ru.neostudy.calculator.controllers.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.neostudy.calculator.dto.RequestParametersDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class RequestWrongArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(-15000),
                                20L,
                                null,
                                null),
                        "avgSalary",
                        "The average salary must be greater than 0.0"
                ),

                Arguments.of(
                        new RequestParametersDto(
                                null,
                                20L,
                                null,
                                null),
                        "avgSalary",
                        "Set up the average salary"
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                81L,
                                null,
                                null),
                        "vacationDays",
                        "The amount of vacation days must be less than 80"
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                -20L,
                                null,
                                null),
                        "vacationDays",
                        "The amount of vacation days must be greater than 0"
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                null,
                                LocalDate.of(2024, 12, 31),
                                LocalDate.of(2024, 12, 1)),
                        "getVacationPay.parameters.endDate is after beginDate",
                        "End of vacation date must be after beginning date"
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                20L,
                                null,
                                LocalDate.of(2024, 12, 31)),
                        "getVacationPay.parameters.selection of general calculation",
                        "Remove date parameters for generals calculation"
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                null,
                                null,
                                null),
                        "getVacationPay.parameters.selection of calculation type",
                        "Set up the number of vacation days or the exact date of leaving on vacation."
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                null,
                                null,
                                LocalDate.of(2024, 12, 31)),
                        "getVacationPay.parameters.beginDate",
                        "Set up the begin date of vacation."
                ),

                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                null,
                                LocalDate.of(2024, 12, 31),
                                null),
                        "getVacationPay.parameters.endDate",
                        "Set up the end date of vacation."
                )
        );
    }
}
