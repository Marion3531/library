package com.example.library.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.library.assembler.BookModelAssembler;
import com.example.library.controller.BookController;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Author;
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
	
	/*@POST
	@Produces("application/json")
	public Article createArticle(@FormParam("titre") String title,
								@FormParam("contenu") String content) {
		
		Article article = service.create(title, content);
		
		article = service.save(article);
		
		return article;
	}*/
	
	// POST
	//String title, String description, @RequestParam("authorId") List<Author> authorIds
	public ResponseEntity<?> createNewBook(Book book){
		
		CollectionModel<EntityModel<Author>> authors = authorService.getAllAuthors();
		
		// compare the authorIds  
		List<Author> matchingAuthors = new ArrayList<>();
	    for (Author author : book.getAuthors()) {
	        if (authors.getContent().stream().anyMatch(a -> a.getContent().getId().equals(author.getId()))) {
	            matchingAuthors.add(author);
	        }
	    }
        /* equivalent of :
         * for (Author author : authors) {
            if (authorIds.contains(author.getId())) {
                matchingAuthors.add(author);
            }
           }*/
	    
	    Book newBook = new Book();
		newBook.setTitle(book.getTitle());
		newBook.setDescription(book.getDescription());
		newBook.setAuthors(matchingAuthors);
		
		EntityModel<Book> entityModel = assembler.toModel(repository.save(newBook));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
		
	}

	// PUT
	public ResponseEntity<?> replaceBook(Book book) { //Book book = new data

		Book updatedBook = repository.findById(book.getId()) 
				
				.map(existingBook -> {
					existingBook.setTitle(book.getTitle());
					existingBook.setDescription(book.getDescription());
					existingBook.setAuthors(book.getAuthors());
					return repository.save(book);
				}) 
				.orElseThrow(() -> new BookNotFoundException(book.getId()));

		EntityModel<Book> entityModel = assembler.toModel(updatedBook);

		return ResponseEntity 
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
				.body(entityModel);
	}

	// DELETE
	public ResponseEntity<?> delBook(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
