package br.com.sistemaerp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Sistema ERP API")
                        .version("1.0.0")
                        .description(
                                "API REST para um Sistema de Gestão Empresarial (ERP), " +
                                "desenvolvida com Java e Spring Boot."
                        ));
    }
}
