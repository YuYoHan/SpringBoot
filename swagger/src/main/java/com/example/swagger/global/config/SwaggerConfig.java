package com.example.swagger.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return  new Docket(DocumentationType.OAS_30)
                // ApiSelectorBuilder 생성
                .select()
                // api 문서화를 할 패키지 경로
                .apis(RequestHandlerSelectors.basePackage())
    }
}
