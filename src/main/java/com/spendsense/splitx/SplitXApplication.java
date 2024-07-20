package com.spendsense.splitx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SplitXApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitXApplication.class, args);
	}

}
