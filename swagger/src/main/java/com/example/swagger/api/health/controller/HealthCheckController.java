package com.example.swagger.api.health.controller;

import com.example.swagger.api.health.dto.HealthCheckResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {
    // 개발환경인지 아니면 운영환경인지 알 기 위해서
    private final Environment environment;

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDTO> healthCheck() {
        HealthCheckResponseDTO healthCheckResponseDTO = HealthCheckResponseDTO.builder()
                .health("ok")
                .activeProFiles(Arrays.asList(environment.getActiveProfiles()))
                .build();
        return ResponseEntity.ok(healthCheckResponseDTO);
    }
}
