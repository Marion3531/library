package com.example.library.exception;

public class InvalidUsernameException extends IllegalArgumentException {

	public InvalidUsernameException () {
		super("username doesn't match security constraints");
	}
}
