package com.example.library.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.User;
import com.example.library.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> all() {
		
	    return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUserbyId(@PathVariable Long id) {
		
		return userService.getUserById(id);
	}
	
	/* useless car méthode register dans AuthenticationController
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		return userService.createUser(user);
	}*/
	
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUser(@PathVariable Long id) {
		
		userService.deleteUser(id);
	}
}
