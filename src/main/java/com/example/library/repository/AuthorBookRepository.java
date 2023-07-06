package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<BookRepository, AuthorRepository> {

}
