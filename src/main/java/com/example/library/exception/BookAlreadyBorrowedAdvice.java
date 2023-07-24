package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BookAlreadyBorrowedAdvice {

	@ResponseBody
	@ExceptionHandler(BookAlreadyBorrowedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bookAlreadyBorrowedHandler(BookAlreadyBorrowedException ex) {
		return ex.getMessage();
	}
	
	/*En résumé, cette classe BookAlreadyBorrowedAdvice sert à capturer l'exception BookAlreadyBorrowedException lancée dans les contrôleurs, 
	 * puis à renvoyer le message de l'exception en tant que réponse JSON avec le code de statut HTTP approprié. 
	 * Cela permet de fournir une réponse claire et informative au client en cas d'emprunt d'un livre déjà emprunté.*/
	
}
