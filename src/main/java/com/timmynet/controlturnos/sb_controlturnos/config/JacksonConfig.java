package com.timmynet.controlturnos.sb_controlturnos.config;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class JacksonConfig {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Bean
    @Primary
    public ObjectMapper defaultObjectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(java.time.LocalDateTime.class, new LocalDateTimeSerializer(FORMATTER));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // No habilitamos indentación aquí (compacto)
        return mapper;
    }

    @Bean(name = "prettyObjectMapper")
    public ObjectMapper prettyPrintObjectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(java.time.LocalDateTime.class, new LocalDateTimeSerializer(FORMATTER));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Para logs solamente
        return mapper;
    }
}
