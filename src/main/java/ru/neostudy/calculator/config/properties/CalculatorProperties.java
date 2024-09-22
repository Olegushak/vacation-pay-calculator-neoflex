package ru.neostudy.calculator.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("calculator")
@Data
public class CalculatorProperties {

    private double numberOfDays;

    private double taxPercent;
}
