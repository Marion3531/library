package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	//@Query("SELECT b.* FROM books b LEFT JOIN author_book ab ON b.id = ab.book_id LEFT JOIN authors a ON ab.author_id = a.id WHERE LOWER(b.title) LIKE '%{query}%' OR LOWER(a.firstname) LIKE '%{query}%' OR LOWER(a.lastname) LIKE '%{query}%';") //using alias ; % = métacaractères pour une close LIKE, meaning que la requête doit effectuer une recherche partielle pour trouver des correspondances partielles plutôt que des correspondances exactes
	@Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %?1%")
	List<Book> searchBookByTitleOrByAuthorsLastname(String query);
	
}
