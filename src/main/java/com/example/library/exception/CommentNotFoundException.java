package com.example.library.exception;


public class CommentNotFoundException extends RuntimeException {

	public CommentNotFoundException(Long id) {
		super("Could not find comment " + id);
	}
}
