package com.jkikms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JkikmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JkikmsApplication.class, args);
	}
}
