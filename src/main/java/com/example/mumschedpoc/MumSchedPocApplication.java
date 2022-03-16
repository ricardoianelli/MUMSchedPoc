package com.example.mumschedpoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MumSchedPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(MumSchedPocApplication.class, args);
    }

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    public Info apiInfo() {
        Info info = new Info();
        info
                .title("MUMSched API")
                .description("MUMSched Open API")
                .version("v0.0.1");
        return info;
    }

}
