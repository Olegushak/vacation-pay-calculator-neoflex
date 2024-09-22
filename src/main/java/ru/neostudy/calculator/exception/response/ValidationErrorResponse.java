package ru.neostudy.calculator.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ValidationErrorResponse {

    private final List<Violation> violations;

}

