package com.example.library.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;

@RestController
public class AuthorController {

	private final AuthorService authorService;

	AuthorController(AuthorService bookService) {
		this.authorService = bookService;
	}
	
	// Aggregate root
    @GetMapping("/authors")
    public CollectionModel<EntityModel<Author>> all() {
        return authorService.getAllAuthors();
    }
    
    //Single Item
    @GetMapping("/authors/{id}")
    public EntityModel<Author> one(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }
    
    @PostMapping("/authors")
	public ResponseEntity<?> createAuthor(@RequestBody Author newBook) {
		return authorService.createNewAuthor(newBook);
	}
    
    @PutMapping("/authors/{id}")
    public ResponseEntity<?> updatereateAuthor(@RequestBody Author newBook, @PathVariable Long id){
    	return authorService.replaceAuthor(newBook, id);
    }
    
    @DeleteMapping("/authors/{id}")
    ResponseEntity<?> deletereateAuthor(@PathVariable Long id){
    	return authorService.delAuthor(id);
    }
}
