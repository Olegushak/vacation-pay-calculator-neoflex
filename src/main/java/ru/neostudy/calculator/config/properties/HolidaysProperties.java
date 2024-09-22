package ru.neostudy.calculator.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties
@Setter
@Getter
public class HolidaysProperties {

    private Set<String> holidays;

}
