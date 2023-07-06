package com.example.library.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.BookService;

@RestController
public class BookController {

	private final BookService bookService;

	BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	// Aggregate root
    @GetMapping("/books")
    public CollectionModel<EntityModel<Book>> all() {
        return bookService.getAllBooks();
    }
    
    //Single Item
    @GetMapping("/books/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book newBook, @RequestParam("authorId") List<Author> authorIds) {
        return bookService.createNewBook(newBook, authorIds);
    }
    
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book newBook, @PathVariable Long id){
    	return bookService.replaceBook(newBook, id);
    }
    
    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id){
    	return bookService.delBook(id);
    }

}
