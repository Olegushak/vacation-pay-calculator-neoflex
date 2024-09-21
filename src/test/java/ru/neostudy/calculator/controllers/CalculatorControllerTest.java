package ru.neostudy.calculator.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import ru.neostudy.calculator.controllers.provider.RequestValidArgumentsProvider;
import ru.neostudy.calculator.controllers.provider.RequestWrongArgumentsProvider;
import ru.neostudy.calculator.dto.RequestParametersDto;
import ru.neostudy.calculator.dto.VacationPayDto;
import ru.neostudy.calculator.exception.response.ValidationErrorResponse;

import java.util.Objects;

@DisplayName("Unit testing for CalculatorControllerTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @ParameterizedTest
    @ArgumentsSource(RequestValidArgumentsProvider.class)
    void calculateShouldReturnValidResponse(RequestParametersDto params,String result) {
        String url = UriComponentsBuilder.fromUriString("/calculate")
                .queryParam("avgSalary", params.getAvgSalary())
                .queryParam("vacationDays", params.getVacationDays())
                .queryParam("beginDate",params.getBeginDate())
                .queryParam("endDate",params.getEndDate())
                .toUriString();
        ResponseEntity<VacationPayDto> vacationPay = restTemplate.getForEntity(url,VacationPayDto.class);

        Assertions.assertEquals(HttpStatus.OK,vacationPay.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON,vacationPay.getHeaders().getContentType());
        Assertions.assertEquals(result, Objects.requireNonNull(vacationPay.getBody()).getTotalPay());




    }

    @ParameterizedTest
    @ArgumentsSource(RequestWrongArgumentsProvider.class)
    void calculateShouldReturnErrorResponse(RequestParametersDto params,String field,String message){
        String url = UriComponentsBuilder.fromUriString("/calculate")
                .queryParam("avgSalary", params.getAvgSalary())
                .queryParam("vacationDays", params.getVacationDays())
                .queryParam("beginDate",params.getBeginDate())
                .queryParam("endDate",params.getEndDate())
                .toUriString();

        ResponseEntity<ValidationErrorResponse> validationErrorResponse = restTemplate.getForEntity(url, ValidationErrorResponse.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,validationErrorResponse.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON,validationErrorResponse.getHeaders().getContentType());
        Assertions.assertEquals(field, Objects.requireNonNull(validationErrorResponse.getBody()).getViolations().get(0).getField());
        Assertions.assertEquals(message, Objects.requireNonNull(validationErrorResponse.getBody()).getViolations().get(0).getMessage());


    }
}
