package com.example.library.exception;

public class PasswordNotSafeException extends IllegalArgumentException {

	public PasswordNotSafeException() {
		super("password doesn't match security constraints");
	}
}
