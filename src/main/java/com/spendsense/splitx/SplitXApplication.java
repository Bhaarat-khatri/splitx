package com.spendsense.splitx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SplitXApplication {
	
	@Value("${client.url}")
    private String clientUrl;

	public static void main(String[] args) {
		SpringApplication.run(SplitXApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins(clientUrl)
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowCredentials(true);
			}
		};
	}

}
