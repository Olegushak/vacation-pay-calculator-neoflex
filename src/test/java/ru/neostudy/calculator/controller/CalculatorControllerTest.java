package ru.neostudy.calculator.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.neostudy.calculator.dto.VacationPaymentRequest;
import ru.neostudy.calculator.dto.VacationPaymentResponse;
import ru.neostudy.calculator.exception.response.ValidationErrorResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit testing for CalculatorControllerTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @MethodSource("requestValidArgumentsProvider")
    void calculateShouldReturnValidResponse(VacationPaymentRequest params, String amount) {
        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/calculate")
                        .queryParam("avgSalary", params.getAvgSalary())
                        .queryParam("vacationDays", params.getVacationDays())
                        .queryParam("beginDate", params.getBeginDate())
                        .queryParam("endDate", params.getEndDate())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(VacationPaymentResponse.class)
                .consumeWith(body -> {
                    assertEquals(amount,
                            Objects.requireNonNull(body.getResponseBody())
                                    .getAmount());
                });
    }

    @ParameterizedTest
    @MethodSource("requestWrongArgumentsProvider")
    void calculateShouldReturnErrorResponse(VacationPaymentRequest params, String message) {
        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/calculate")
                        .queryParam("avgSalary", params.getAvgSalary())
                        .queryParam("vacationDays", params.getVacationDays())
                        .queryParam("beginDate", params.getBeginDate())
                        .queryParam("endDate", params.getEndDate())
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ValidationErrorResponse.class)
                .consumeWith(body -> {
                    assertEquals(message,
                            Objects.requireNonNull(body.getResponseBody())
                                    .getViolations()
                                    .get(0)
                                    .getMessage());
                });
    }

    static Stream<? extends Arguments> requestValidArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                20,
                                null,
                                null),
                        "8908.00"
                ),
                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                null,
                                LocalDate.of(2024, 12, 1),
                                LocalDate.of(2024, 12, 31)),
                        "12916.55"
                )
        );
    }

    static Stream<? extends Arguments> requestWrongArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(-15000),
                                20,
                                null,
                                null),
                        "The average salary must be greater than 0.0"
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                null,
                                20,
                                null,
                                null),
                        "Set up the average salary"
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                81,
                                null,
                                null),
                        "The amount of vacation days must be less than 80"
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                -20,
                                null,
                                null),
                        "The amount of vacation days must be greater than 0"
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                null,
                                LocalDate.of(2024, 12, 31),
                                LocalDate.of(2024, 12, 1)),
                        "End of vacation date must be after beginning date"
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                20,
                                null,
                                LocalDate.of(2024, 12, 31)),
                        "Remove date parameters for generals calculation"
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                null,
                                null,
                                null),
                        "Set up the number of vacation days or the exact date of leaving on vacation."
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                null,
                                null,
                                LocalDate.of(2024, 12, 31)),
                        "Set up the begin date of vacation."
                ),

                Arguments.of(
                        new VacationPaymentRequest(
                                BigDecimal.valueOf(15000),
                                null,
                                LocalDate.of(2024, 12, 31),
                                null),
                        "Set up the end date of vacation."
                )
        );
    }
}
