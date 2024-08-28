package com.Graduation_Be;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${RENDER_EXTERNAL_URL:http://localhost:8080}")
    private String renderExternalUrl;

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("My API Graduation")
                        .description("API Graduation")
                        .version("1.0"))
                .servers(List.of(new Server().url(renderExternalUrl)));

    }
}