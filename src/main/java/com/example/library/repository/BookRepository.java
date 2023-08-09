package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.library.dto.BookAvailability;
import com.example.library.dto.BookProjection;
import com.example.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b LEFT JOIN b.authors a WHERE LOWER(b.title) LIKE ?1 OR LOWER(a.firstname) LIKE ?1 OR LOWER(a.lastname) LIKE ?1")
	List<Object[]> searchBookByTitleOrByAuthorsLastname(String query);

	@Query(value = "SELECT books.id, books.title, books.description, "
			+ "CASE WHEN COUNT(loans.id) > 0 THEN true ELSE false END AS isBorrowed " + "FROM books "
			+ "LEFT JOIN loans ON books.id = loans.book_id AND loans.is_borrowed = true "
			+ "GROUP BY books.id", nativeQuery = true)
	List<BookProjection> findAllBooksWithBorrowStatus();

	// to go with DTO technique
	@Query("""
			    SELECT b, (CASE WHEN COUNT(l.id) > 0 THEN true ELSE false END)
			    FROM Book b
			    LEFT JOIN Loan l ON b.id = l.book.id AND l.isBorrowed = true
			    GROUP BY b
			""")
	List<Object[]> findAllWithAvailability();

	// to go with projection technique
	@Query("""
			    SELECT b AS book, (CASE WHEN COUNT(l.id) > 0 THEN true ELSE false END) AS isBorrowed
			    FROM Book b
			    LEFT JOIN Loan l ON b.id = l.book.id AND l.isBorrowed = true
			    GROUP BY b.id
			""")
	List<BookAvailability> findAllWithAvailabilityProjection();

}
