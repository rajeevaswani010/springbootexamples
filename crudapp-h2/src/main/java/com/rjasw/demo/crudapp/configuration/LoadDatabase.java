package com.rjasw.demo.crudapp.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rjasw.demo.crudapp.entity.Customer;
import com.rjasw.demo.crudapp.repo.CustomerRepo;

@Configuration
public class LoadDatabase {
	Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	public CommandLineRunner demo(CustomerRepo repository) {
		return (args) -> {
			
			//save a few customers
		      repository.save(new Customer("Jack", "Bauer"));
		      repository.save(new Customer("Chloe", "O'Brian"));
		      repository.save(new Customer("Kim", "Bauer"));
		      repository.save(new Customer("David", "Palmer"));
		      repository.save(new Customer("Michelle", "Dessler"));
		      
		      //fetch all customers
		      log.info("Customers found with findAll():");
		      log.info("-------------------------------");
		      repository.findAll().forEach(customer -> {
		        log.info(customer.toString());
		      });
		      log.info("");
		      
		      // fetch an individual customer by ID
		      Customer customer = repository.findById(1L).orElse(null);
		      log.info("Customer found with findById(1L):");
		      log.info("--------------------------------");
		      log.info(customer.toString());
		      log.info("");

		      // fetch customers by last name
		      log.info("Customer found with findByLastName('Bauer'):");
		      log.info("--------------------------------------------");
		      repository.findByLastName("Bauer").forEach(bauer -> {
		        log.info(bauer.toString());
		      });
		      log.info("");
		};
	} //end -- commandline runner

}
