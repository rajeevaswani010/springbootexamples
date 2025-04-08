package com.rjasw.demo.crudapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudappApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CrudappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrudappApplication.class, args);
	}
}
