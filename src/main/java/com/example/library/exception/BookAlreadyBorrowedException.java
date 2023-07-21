package com.example.library.exception;

public class BookAlreadyBorrowedException extends RuntimeException{

	public BookAlreadyBorrowedException(Long id) {
		super("Book " + id + " is already borrowed");
	}
}
