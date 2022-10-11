package edu.miu.accountservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PreDestroy
    public void onExit() {
        log.info("Cleaning up database....");
        log.info("Database cleaned!");
    }
}
