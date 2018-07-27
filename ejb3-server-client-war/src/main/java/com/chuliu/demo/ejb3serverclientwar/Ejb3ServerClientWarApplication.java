package com.chuliu.demo.ejb3serverclientwar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Ejb3ServerClientWarApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Ejb3ServerClientWarApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Ejb3ServerClientWarApplication.class, args);
	}
}
