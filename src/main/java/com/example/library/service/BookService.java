package com.example.library.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.library.assembler.BookModelAssembler;
import com.example.library.controller.BookController;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository repository;
	private final BookModelAssembler assembler;
	
	@Autowired
	private AuthorService authorService;

	public BookService(BookRepository repository, BookModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	public CollectionModel<EntityModel<Book>> getAllBooks() {

		List<EntityModel<Book>> books = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
	}

	// Single item
	public EntityModel<Book> getBookById(Long id) {
		Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
		return assembler.toModel(book);
	}

	// POST
	public ResponseEntity<?> createNewBook(@RequestBody Book newBook) {

		EntityModel<Book> entityModel = assembler.toModel(repository.save(newBook));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	/*public ResponseEntity<?> createNewBook(@RequestBody Book newBook, @RequestParam("authorId") Long authorId) {
		
	    // Récupérer l'auteur à partir de l'ID
	    Author author = authorService.getAuthorById(authorId).getContent();

	    // Associer l'auteur au nouveau livre
	    newBook.setAuthor(author);

		EntityModel<Book> entityModel = assembler.toModel(repository.save(newBook));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}*/

	// PUT
	public ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable Long id) {

		Book updatedBook = repository.findById(id) //
				.map(book -> {
					book.setTitle(newBook.getTitle());
					book.setDescription(newBook.getDescription());
					return repository.save(book);
				}) //
				.orElseGet(() -> {
					newBook.setId(id);
					return repository.save(newBook);
				});

		EntityModel<Book> entityModel = assembler.toModel(updatedBook);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// DELETE
	public ResponseEntity<?> delBook(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
