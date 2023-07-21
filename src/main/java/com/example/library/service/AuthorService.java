package com.example.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.library.exception.AuthorNotFoundException;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;

@Service
public class AuthorService {

	private final AuthorRepository repository;
	
	public AuthorService(AuthorRepository repository) {
		this.repository = repository;
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

		return repository.save(newAuthor);
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
	public void deleteAuthorById(@PathVariable Long id) {

		repository.deleteById(id);;
	}
}
