package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PasswordNotSafeAdvice {
	
	  @ResponseBody
	  @ExceptionHandler(PasswordNotSafeException.class)
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  String asswordNotSafeHandler(PasswordNotSafeException ex) {
	    return ex.getMessage();
	  }
}
