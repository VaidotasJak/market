package com.skydive.market.configs;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;


import java.util.Arrays;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Skydive market API")
                        .description("List of APIs for deeplearning4j testing")
                        .version("v1.0.0")
                        .contact(new Contact())
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8080").description("skydive market testing")

                ));
    }


}