package com.example.library.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.assembler.AuthorModelAssembler;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;

@RestController
public class AuthorController {

	private final AuthorService authorService;
	private final AuthorModelAssembler assembler;
	private final AuthorRepository repository;

	AuthorController(AuthorService bookService, AuthorModelAssembler assembler, AuthorRepository repository) {
		this.authorService = bookService;
		this.assembler = assembler;
		this.repository = repository;
	}
	
	// Aggregate root
    @GetMapping("/authors")
    public CollectionModel<Author> all() {
    	
    	List<Author> authors = authorService.getAllAuthors();
    	
        return CollectionModel.of(authors, linkTo(methodOn(AuthorController.class).all()).withSelfRel());
    }
    
    //Single Item
    @GetMapping("/authors/{id}")
    public EntityModel<Author> one(@PathVariable Long id) {
    	
    	Author author = authorService.getAuthorById(id);
    	
        return assembler.toModel(author);
    }
    
    @PostMapping("/authors")
	public ResponseEntity<?> createAuthor(@RequestBody Author newAuthor) {
    	
    	Author author = authorService.createNewAuthor(newAuthor);
    	
    	EntityModel<Author> entityModel = assembler.toModel(repository.save(newAuthor));
    	
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(author);
	}
    
    @PutMapping("/authors/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author author){
        
    	Author updatedAuthor = authorService.replaceAuthor(id, author);
        
    	EntityModel<Author> entityModel = assembler.toModel(updatedAuthor);
        
    	return ResponseEntity.ok(entityModel);
    }

    

    
    @DeleteMapping("/authors/{id}")
    ResponseEntity<?> deleteAuthor(@PathVariable Long id){
    	
    	authorService.deleteAuthorById(id);
    	
    	return ResponseEntity.noContent().build();
    }
}
