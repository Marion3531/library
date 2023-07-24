package com.example.library.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Long id) {
		
		super("Could not find User " + id);
	}
}
