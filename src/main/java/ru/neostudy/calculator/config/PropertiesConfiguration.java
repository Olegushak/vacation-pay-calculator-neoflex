package ru.neostudy.calculator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.neostudy.calculator.config.properties.CalculatorProperties;
import ru.neostudy.calculator.config.properties.HolidaysProperties;

@Configuration
@EnableConfigurationProperties(
        {CalculatorProperties.class,
                HolidaysProperties.class}
)
public class PropertiesConfiguration {
}
