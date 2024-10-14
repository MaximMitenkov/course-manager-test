package org.mitenkov.coursemanager.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Course Manager")
                .version("1.0")
                .description("My test application for CIRCUS")
                .summary("Test application");

        return new OpenAPI().info(info);
    }

}
