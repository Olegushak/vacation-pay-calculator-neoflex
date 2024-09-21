package ru.neostudy.calculator.controllers.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.neostudy.calculator.dto.RequestParametersDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class RequestValidArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                20L,
                                null,
                                null),
                        "8908.00 RUB"
                ),
                Arguments.of(
                        new RequestParametersDto(
                                BigDecimal.valueOf(15000),
                                null,
                                LocalDate.of(2024, 12, 1),
                                LocalDate.of(2024, 12, 31)),
                        "12916.55 RUB"
                )
        );
    }
}
