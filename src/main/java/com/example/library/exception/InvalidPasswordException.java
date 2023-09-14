package com.example.library.exception;

public class InvalidPasswordException extends IllegalArgumentException {

	public InvalidPasswordException() {
		super("password doesn't match security constraints");
	}
}
