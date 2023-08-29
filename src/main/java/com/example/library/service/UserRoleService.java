package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.exception.CommentNotFoundException;
import com.example.library.model.UserRole;
import com.example.library.repository.UserRoleRepository;

@Service
public class UserRoleService {

	private final UserRoleRepository repository;
	
	public UserRoleService(UserRoleRepository repository) {
		
		this.repository = repository;
	}
	
	public List<UserRole> getAllUserRoles() {
		
		return repository.findAll();
	}
	
	public UserRole getUserRoleById(Long id) {
		
		UserRole userRole = repository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
		
		return userRole;
	}
	
	public UserRole createUserRole(String role) {
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		
		return repository.save(userRole);
	}
	
}
