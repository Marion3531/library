package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.UserRole;
import com.example.library.service.UserRoleService;

@RestController
public class UserRoleController {

	private final UserRoleService service;
	
	public UserRoleController (UserRoleService service) {
		
		this.service = service;
	}
	
	@GetMapping("/userRoles")
	public List<UserRole> getAll() {
		
		return service.getAllUserRoles();
	}
	
	@GetMapping("/userRoles/{id}")
	public UserRole getById(@PathVariable Long id) {
		
		return service.getUserRoleById(id);
	}
	
	@PostMapping("/userRoles")
	public UserRole newUserRole(@RequestBody String role) {
		
		return service.createUserRole(role);
	}
	
}
