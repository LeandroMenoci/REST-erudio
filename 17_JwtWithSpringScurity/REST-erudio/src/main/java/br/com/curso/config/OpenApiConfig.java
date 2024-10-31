package br.com.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration // Anotação que indica que esta classe é uma configuração do Spring
public class OpenApiConfig {

    // Método que configura o OpenAPI
    @Bean // Indica que este método retorna um bean que será gerenciado pelo Spring
    public OpenAPI customOpenAPI() {
        // Criação de uma nova instância de OpenAPI
        return new OpenAPI()
            .info(new Info() // Define as informações básicas da API
                .title("RESTful API with Java and Spring Boot") // Título da API
                .version("v1") // Versão da API
                .description("Description about API") // Descrição da API
                .termsOfService("https://google.com") // URL dos termos de serviço
                .license(new License() // Configuração da licença
                    .name("Apache 2.0") // Nome da licença
                    .url("https://google.com"))); // URL da licença
    }
}
