package com.example.library.service;

import com.example.library.dto.BookAvailability;
import com.example.library.dto.BookDTO;
import com.example.library.dto.BookProjection;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.utils.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<BookProjection> getAllBooksDTO() {
		List<BookProjection> booksDTO = repository.findAllBooksWithBorrowStatus();
		return booksDTO;
	}

    public List<BookDTO> getBooks() {
        List<Object[]> bookObjects = repository.findAllWithAvailability();
		return bookObjects.stream()
            .map(bookObject -> {
                Book book = (Book) bookObject[0];
                return new BookDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getDescription(),
                    book.getAuthors(),
                    (boolean) bookObject[1]
                );
            })
            .toList();
    }

    public List<BookDTO> getBooksProjection() {
        List<BookAvailability> bookAvailabilities = repository.findAllWithAvailabilityProjection();
        return bookAvailabilities.stream()
            .map(Transformer::toDto)
            .toList();
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
