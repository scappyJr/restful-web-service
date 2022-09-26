package com.example.restfulwebservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("RESTful Web Service Application")
                .version(springdocVersion)
                .description("Spring Boot2를 이용한 REST API 서비스 구현");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
