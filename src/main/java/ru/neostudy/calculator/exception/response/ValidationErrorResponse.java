package ru.neostudy.calculator.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Setter
public class ValidationErrorResponse {

    private final List<Violation> violations;

}

