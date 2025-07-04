package com.timmynet.controlturnos.sb_controlturnos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI()
            .info(new Info()
                .title("Control de Turnos API")
                .version("1.0.0")
                .description("API para gestionar el control de turnos de colaboradores."));

    }
}
