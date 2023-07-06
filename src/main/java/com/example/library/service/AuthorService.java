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
	public CollectionModel<EntityModel<Author>> getAllAuthors() {

		List<EntityModel<Author>> authors = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(authors, linkTo(methodOn(AuthorController.class).all()).withSelfRel());
	}
	
	// Single item
	public EntityModel<Author> getAuthorById(Long id) {
		Author author = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
		return assembler.toModel(author);
	}

	// POST
	public ResponseEntity<?> createNewAuthor(@RequestBody Author newAuthor) {

		EntityModel<Author> entityModel = assembler.toModel(repository.save(newAuthor));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// PUT
	public ResponseEntity<?> replaceAuthor(@RequestBody Author newAuthor, @PathVariable Long id) {

	    Author updatedAuthor = repository.findById(id)
	            .map(author -> {
	                author.setFirstname(newAuthor.getFirstname());
	                author.setLastname(newAuthor.getLastname());
	                return repository.save(author);
	            })
	            .orElseGet(() -> {
	                newAuthor.setId(id);
	                return repository.save(newAuthor);
	            });

	    EntityModel<Author> entityModel = assembler.toModel(updatedAuthor);

	    return ResponseEntity
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
	            .body(entityModel);
	}

	// DELETE
	public ResponseEntity<?> delAuthor(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
