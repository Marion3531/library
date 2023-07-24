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
	
	public User createUser(User user) {
		
		User newUser = new User();
		
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		
		return repository.save(newUser);
	}
	
	public User updateUser(Long id, User user) {
		
		User updatedUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		if (user.getUsername() != null) {
			updatedUser.setUsername(user.getUsername());
		}
		
		if (user.getEmail() != null) {
			updatedUser.setEmail(user.getEmail());
		}
		
		return repository.save(updatedUser);
	}
	
	public void deleteUser(Long id) {
		
		repository.deleteById(id);
	}
}
