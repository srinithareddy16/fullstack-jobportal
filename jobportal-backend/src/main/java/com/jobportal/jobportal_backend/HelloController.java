package com.jobportal.jobportal_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	

	@GetMapping("/hello")
	public String hello() {
		return "Hello, Srinitha! Spring Boot is working!";
	}

}
