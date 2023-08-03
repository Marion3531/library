package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.library.dto.BookDTO;
import com.example.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b LEFT JOIN b.authors a WHERE LOWER(b.title) LIKE ?1 OR LOWER(a.firstname) LIKE ?1 OR LOWER(a.lastname) LIKE ?1")
	List<Book> searchBookByTitleOrByAuthorsLastname(String query);

	@Query(value = "SELECT books.id, books.title, books.description, "
			+ "CASE WHEN COUNT(loans.id) > 0 THEN true ELSE false END AS is_borrowed " + "FROM books "
			+ "LEFT JOIN loans ON books.id = loans.book_id AND loans.is_borrowed = true "
			+ "GROUP BY books.id", nativeQuery = true)
	List<BookDTO> findAllBooksWithBorrowStatus();

}
