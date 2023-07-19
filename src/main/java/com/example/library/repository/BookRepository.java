package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %?1%") //using alias ; % = métacaractères pour une close LIKE, meaning que la requête doit effectuer une recherche partielle pour trouver des correspondances partielles plutôt que des correspondances exactes
	List<Book> searchBookByTitle(String query);
	
}
