package com.theZ.dotoring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class DotoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DotoringApplication.class, args);
	}

}

