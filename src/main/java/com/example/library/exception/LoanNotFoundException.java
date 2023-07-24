package com.example.library.exception;

public class LoanNotFoundException extends RuntimeException{
	
	public LoanNotFoundException(Long id) {
		
		super("Could not find loan number " + id);
	}

}
