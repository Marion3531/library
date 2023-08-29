package com.example.library.exception;

public class UserRoleNotFoundException extends RuntimeException {

	public UserRoleNotFoundException(Long id) {
		
		super("Could not find user role " + id);
	}
}
