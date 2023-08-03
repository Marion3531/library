package com.example.library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.library.dto.BookDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
@Transactional
public class BookService {
	
	private final BookRepository repository;
	private AuthorService authorService;
	
	public BookService(BookRepository repository, AuthorService authorService) {
		this.repository = repository;
		this.authorService = authorService;
	}

	// Aggregate root
	public List<Book> getAllBooks() {

		List<Book> books = repository.findAll();

		return books;
	}
	
	public List<BookDTO> getAllBooksDTO() {
		
		List<BookDTO> booksDTO = repository.findAllBooksWithBorrowStatus();
		
		return booksDTO;
	}

	// Single item
	public Book getBookById(Long id) {

		Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

		return book;
	}

	// SEARCH
	public List<Book> searchBookByTitle(String query) {
		
		return repository.searchBookByTitleOrByAuthorsLastname("%" + query + "%");
	}
	
	// POST
	public Book createNewBook(Book book) {

		List<Author> authors = authorService.getAllAuthors();

		// compare the authorIds
		List<Author> matchingAuthors = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			if (authors.stream().anyMatch(a -> a.getId().equals(author.getId()))) {
				matchingAuthors.add(author);
			}
		}

		Book newBook = new Book();
		newBook.setTitle(book.getTitle());
		newBook.setDescription(book.getDescription());
		newBook.setAuthors(matchingAuthors);

		return repository.save(newBook);
	}

	// UPDATE BOOK(PUT)
	public Book replaceBook(Long id, Book book) { // Book book = new data

		Book updatedBook = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

		if (book.getTitle() != null) {
			updatedBook.setTitle(book.getTitle());
		}

		if (book.getDescription() != null) {
			updatedBook.setDescription(book.getDescription());
		}

		if (book.getAuthors() != null) {
			updatedBook.setAuthors(book.getAuthors());

		}
	    
		return repository.save(updatedBook);
	}

	// DELETE
	public void deleteBookById(@PathVariable Long id) {

		repository.deleteById(id);
	}

}
