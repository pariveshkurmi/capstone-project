package com.capstone.project.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.capstone.project.web")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
