package com.agenda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Agenda API")
                .version("1.0.0")
                .description("Documentación de la API de la agenda")
                .contact(new Contact()
                    .name("Nombre del contacto")
                    .email("email@example.com")
                    .url("http://example.com")));
    }
}
