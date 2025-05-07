package com.abhishekvermaa10.config;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author abhishekvermaa10
 */
@Configuration
public class HttpExchangeConfig {

	@Bean
	HttpExchangeRepository httpTraceRepository() {
        return new InMemoryHttpExchangeRepository();
    }
	
}
