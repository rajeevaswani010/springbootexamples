package com.rjasw.demo.crudapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rjasw.demo.crudapp.entity.Customer;
import com.rjasw.demo.crudapp.repo.CustomerRepo;

@RestController
public class CustomerController {
		
		Logger log = LoggerFactory.getLogger(CustomerController.class);
	
		public CustomerRepo customerRepo;
		
		public CustomerController(CustomerRepo repo) {
			// TODO Auto-generated constructor stub
			this.customerRepo = repo;
		}
		
		@GetMapping("/customers")
		List<Customer> all(){
			log.info("calling getall");
			return StreamSupport
				.stream(customerRepo.findAll().spliterator(), false)
	            .collect(Collectors.toList())
	            ;
	
		}
		
		@PostMapping(value = "/customers")
		Customer newCustomer(@RequestBody Customer data) {
			log.info("calling new");
			return customerRepo.save(data);
		}
		
		@GetMapping("/customers/{id}")
		Customer getById(@PathVariable Long id) {
			return customerRepo.findById(id).orElse(null);
		}
		
		@PutMapping("/customers/{id}")
		Customer updateCustomer(@PathVariable Long id, @RequestBody Customer data) {
			log.info("inside put");
			return customerRepo.findById(id)
					.map(customer -> {
						customer.setFirstName(data.getFirstName());
						customer.setLastName(data.getLastName());
						return customerRepo.save(customer);
					})
					.orElseGet(() -> {
						return customerRepo.save(data);
					});
		}

		@DeleteMapping("/customers/{id}")
		void deleteCustomer(@PathVariable Long id) {
			log.info("inside delete");
			customerRepo.deleteById(id);
		}
}
