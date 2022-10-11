package edu.miu.productservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PreDestroy;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @PreDestroy
    public void onExit() {
        jdbcTemplate.execute("DELETE FROM product");
    }
}
