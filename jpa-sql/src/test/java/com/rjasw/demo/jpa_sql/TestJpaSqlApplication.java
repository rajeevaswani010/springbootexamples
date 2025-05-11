package com.rjasw.demo.jpa_sql;

import org.springframework.boot.SpringApplication;

public class TestJpaSqlApplication {

	public static void main(String[] args) {
		SpringApplication.from(JpaSqlApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
