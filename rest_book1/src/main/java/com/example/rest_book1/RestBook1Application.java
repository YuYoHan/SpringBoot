package com.example.rest_book1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// JPA Auditing 활성화
@EnableJpaAuditing
public class RestBook1Application {

    public static void main(String[] args) {
        SpringApplication.run(RestBook1Application.class, args);
    }

}
