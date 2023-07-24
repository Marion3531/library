package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b LEFT JOIN b.authors a WHERE LOWER(b.title) LIKE %?1% OR LOWER(a.firstname) LIKE %?1% OR LOWER(a.lastname) LIKE %?1%")
	List<Book> searchBookByTitleOrByAuthorsLastname(String query);
	
}
