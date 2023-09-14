package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidUsernameAdvice {

	  @ResponseBody
	  @ExceptionHandler(InvalidUsernameException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  String invalidUsernameHandler(InvalidUsernameException ex) {
	    return ex.getMessage();
	  }
}
