package com.jr.jobresearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JobresearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobresearchApplication.class, args);
	}

}
