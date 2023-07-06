package com.example.library.exception;

public class AuthorNotFoundException extends RuntimeException {

	public AuthorNotFoundException(Long id) {
		super("Could not find author " + id);
	}
}
