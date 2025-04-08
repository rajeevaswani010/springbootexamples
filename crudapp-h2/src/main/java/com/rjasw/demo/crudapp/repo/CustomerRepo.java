package com.rjasw.demo.crudapp.repo;

import org.springframework.data.repository.CrudRepository;

import com.rjasw.demo.crudapp.entity.Customer;
import java.util.List;
import java.util.Optional;


public interface CustomerRepo extends CrudRepository<Customer, Long> {
	
		List<Customer> findByLastName(String lastName);

		Optional<Customer> findById(Long id);
}
