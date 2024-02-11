package com.medcoanalytics.lemobileyeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class lemobileyeservice {

	public static void main(String[] args) {
    SpringApplication.run(com.medcoanalytics. lemobileyeservice.lemobileyeservice.class, args);
	}

}
