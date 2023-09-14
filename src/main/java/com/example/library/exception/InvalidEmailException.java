package com.example.library.exception;

public class InvalidEmailException extends IllegalArgumentException {

	public InvalidEmailException() {
		super("email address doesn't match security constraints");
	}
}
