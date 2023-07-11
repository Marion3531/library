package com.example.library.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.library.assembler.AuthorModelAssembler;
import com.example.library.controller.AuthorController;
import com.example.library.exception.AuthorNotFoundException;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;

@Service
public class AuthorService {

	private final AuthorRepository repository;
	private final AuthorModelAssembler assembler;
	
	public AuthorService(AuthorRepository repository, AuthorModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	public List<Author> getAllAuthors() {

		List<Author> authors = repository.findAll();

		return authors;
	}
	
	// Single item
	public Author getAuthorById(Long id) {
		
		Author author = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
		
		return author;
	}

	// POST
	public Author createNewAuthor(@RequestBody Author author) {

		//Author entityModel = assembler.toModel(repository.save(newAuthor));

		Author newAuthor = new Author();
		newAuthor.setFirstname(author.getFirstname());
		newAuthor.setLastname(author.getLastname());
		
		newAuthor = repository.save(newAuthor);

		return newAuthor;
	}

	// PUT
	public Author replaceAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {

	    Author currentAuthor = repository.findById(id)
	    		.orElseThrow(() -> new AuthorNotFoundException(id));

	    if (updatedAuthor.getFirstname() != null) {
	    	currentAuthor.setFirstname(updatedAuthor.getFirstname());
	    }
	    
	    if (updatedAuthor.getLastname() != null) {
	    	currentAuthor.setLastname(updatedAuthor.getLastname());
	    }
	    
	    return repository.save(currentAuthor) ;
	}

	// DELETE
	public ResponseEntity<?> delAuthor(@PathVariable Long id) {

		return ResponseEntity.noContent().build();
	}
}
