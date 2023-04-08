package com.example.swagger.global.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
