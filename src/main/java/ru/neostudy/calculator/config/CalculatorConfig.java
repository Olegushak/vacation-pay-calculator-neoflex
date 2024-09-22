package ru.neostudy.calculator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.neostudy.calculator.config.properties.CalculatorProperties;

@Configuration
@EnableConfigurationProperties(CalculatorProperties.class)
public class CalculatorConfig {
}
