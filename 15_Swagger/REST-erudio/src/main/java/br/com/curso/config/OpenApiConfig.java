package br.com.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("RESTful API with Java and Spring Boot")
                .version("v1")
                .description("Description about API")
                .termsOfService("https://google.com")
                .license(new License().name("Apache 2.0")
                    .url("https://google.com")));
    }
}
