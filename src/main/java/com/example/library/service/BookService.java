package com.example.library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository repository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorService authorService;

	public BookService(BookRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	public List<Book> getAllBooks() {

		List<Book> books = repository.findAll();

		return books;
	}

	// Single item
	public Book getBookById(Long id) {

		Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

		return book;
	}

	public List<Book> searchBookByName(String query) {
		
		List<Book> books = repository.findAll();
		
		List<Book> booksFound = new ArrayList<>();
		
		for (Book book : books) {
			if(book.getTitle().equalsIgnoreCase(query)) {
				booksFound.add(book);
			}else {
				for (Author author : book.getAuthors()) {
					if (author.getFirstname().equalsIgnoreCase(query) || author.getLastname().equalsIgnoreCase(query)){
						booksFound.addAll(author.getBooks());
					}
				}
			}
				
	}

	if(booksFound.isEmpty())

	{
		return books;
	}else
	{
		return booksFound;
	}
	}

	// POST
	public Book createNewBook(Book book){
		
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
		
		return newBook;
	}

	// PUT
	public Book replaceBook(Long id, Book updatedBook) { //Book book = new data

		Book currentBook = repository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
		
		if (updatedBook.getTitle() != null) {
			currentBook.setTitle(updatedBook.getTitle());
		}
		
		if (updatedBook.getDescription() != null) {
			currentBook.setDescription(updatedBook.getDescription());
		}
		
		if (updatedBook.getAuthors() != null) {
			currentBook.setAuthors(updatedBook.getAuthors());

		}

		return repository.save(currentBook);
	}

	// DELETE
	public void deleteBookById(@PathVariable Long id) {

		repository.deleteById(id);
	}

}
