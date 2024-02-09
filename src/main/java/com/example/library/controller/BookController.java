package com.example.library.controller;

import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.assembler.BookModelAssembler;
import com.example.library.assembler.LoanModelAssembler;
import com.example.library.dto.BookDTO;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	private final BookService bookService;
	private final BookModelAssembler assembler;
	private final LoanService loanService;
	private final LoanModelAssembler loanAssembler;

	BookController(BookService bookService, 
			BookModelAssembler assembler, 
			LoanService loanService,
			LoanModelAssembler loanAssembler) {
		this.bookService = bookService;
		this.assembler = assembler;
		this.loanService = loanService;
		this.loanAssembler = loanAssembler;
	}

	@GetMapping("/books")
	public List<BookDTO> all(@RequestParam(required = false) String query) {

		if (query != null) {

			return bookService.searchBooksByTitleOrAuthors(query);
		} else {
			return bookService.getBooks();
		}

	}

	// Single Item
	@GetMapping("/books/{id}")
	public EntityModel<Book> one(@PathVariable Long id) {

		Book book = bookService.getBookById(id);

		return assembler.toModel(book);
	}

	/*
	 * SEARCH(GET) [méthode à l'origine]
	 * 
	 * @GetMapping("/books/search") public CollectionModel<EntityModel<Book>>
	 * searchBook(@RequestParam("query") String query) {
	 * 
	 * List<Book> booksFound = bookService.searchBookByTitle(query);
	 * 
	 * List<EntityModel<Book>> bookModels =
	 * booksFound.stream().map(assembler::toModel).collect(Collectors.toList());
	 * 
	 * return CollectionModel.of(bookModels,
	 * linkTo(methodOn(BookController.class).all()).withSelfRel()); }
	 */

	@PostMapping("/books")
	public ResponseEntity<?> createBook(@RequestBody Book book) {

		Book newBook = bookService.createNewBook(book);

		EntityModel<Book> entityModel = assembler.toModel(newBook);

		return ResponseEntity.created(entityModel.getRequiredLink(
				IanaLinkRelations.SELF).toUri()).body(newBook);

	}

	// BORROW A BOOK(POST)
	@PostMapping("/books/borrow/{bookId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @AuthenticationPrincipal User user) {

		Loan loan = loanService.createLoan(bookId, user);

		EntityModel<Loan> entityModel = loanAssembler.toModel(loan);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(loan);

	}

	// RETURN A BOOK(PUT)
	@PutMapping("/books/return/{loanId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> returnBook(@PathVariable Long loanId) {

		loanService.updateLoanToFalse(loanId);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/books/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book) {

		Book updatedBook = bookService.replaceBook(id, book);

		EntityModel<Book> entityModel = assembler.toModel(updatedBook);

		return ResponseEntity.ok(entityModel);
	}

	@DeleteMapping("/books/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {

		bookService.deleteBookById(id);

		return ResponseEntity.noContent().build();
	}

}
