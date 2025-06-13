package com.graphixstory.cursos.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Prueba de API de cursos")
                .version("0.1")
                .description("Documentación de la API para la gestión de cursos de Edutech Innovators SPA"));
    }
}
