package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<User> all() {
		
	    return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public User user(@PathVariable Long id) {
		
		return userService.getUserById(id);
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		return userService.createUser(user);
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, User user) {
		
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		
		userService.deleteUser(id);
	}
}
