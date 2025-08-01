package com.jobportal.jobportal_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class JobportalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobportalBackendApplication.class, args);
	}

}
