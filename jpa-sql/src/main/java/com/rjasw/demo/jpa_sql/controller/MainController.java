package com.rjasw.demo.jpa_sql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rjasw.demo.jpa_sql.entity.User;
import com.rjasw.demo.jpa_sql.repo.UserRepo;

@Controller
@RequestMapping(path="/demo/user") // This means URL's start with /demo (after Application path)
public class MainController {
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
			// @ResponseBody means the returned String is the response, not a view name     // @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepo.save(n);
		return "Saved";
	}
	
	@GetMapping
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
	    return userRepo.findAll();
	}	

    @GetMapping("/{id}")
	public @ResponseBody ResponseEntity<User> get(@RequestParam int id) {
		// This returns a JSON or XML with the users
	    return userRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}	

}

