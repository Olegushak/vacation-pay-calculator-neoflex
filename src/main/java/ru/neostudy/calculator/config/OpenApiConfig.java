package ru.neostudy.calculator.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Vacation payment calculator",
                description = "API allows to calculate vacation payment", version = "1.0.0",
                contact = @Contact(
                        name = "Ushakov Oleg",
                        email = "ushakovo1995@gmail.com",
                        url = "https://github.com/Olegushak"
                )
        )
)
@Configuration
public class OpenApiConfig {


}
