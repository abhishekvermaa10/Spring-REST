package com.scaleupindia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author abhishekvermaa10
 *
 */
@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class Demo implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
	}
}
