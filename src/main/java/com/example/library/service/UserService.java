package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.exception.UserNotFoundException;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> getAllUsers(){
		
		return repository.findAll();
	}
	
	public User getUserById(Long id) {
		
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		return user;
	}
	
	public User getUserByUsername(String username) {
		
		User user = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		
		return user;
	}
	
	/* useless car méthode register dans AuthenticationService
	public User createUser(User user) {
		
		User newUser = new User();
		
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		
		UserRole userRole = userRoleRepository.findById(1L).orElseThrow(() -> new UserRoleNotFoundException(1L));
		newUser.setUserRole(userRole); //set userRole "user" by default
		
		return repository.save(newUser);
	}*/
	
	public User updateUser(Long id, User newUserData) {
		
		User updatedUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		if (newUserData.getUsername() != null) {
			updatedUser.setUsername(newUserData.getUsername());
		}
		
		if (newUserData.getEmail() != null) {
			updatedUser.setEmail(newUserData.getEmail());
		}
		
		if (newUserData.getPassword() != null) {
			updatedUser.setPassword(newUserData.getPassword());
		}
		
		if (newUserData.getRole() != null) {
			updatedUser.setRole(newUserData.getRole());
		}
		
		/*if (newUserData.getUserRole() != null) {
			updatedUser.setUserRole(newUserData.getUserRole());
		}*/
	
		return repository.save(updatedUser);
	}
	
	public void deleteUser(Long id) {
		
		repository.deleteById(id);
	}
}
