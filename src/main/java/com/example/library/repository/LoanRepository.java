package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Book;
import com.example.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

	List<Loan> findByBookAndIsBorrowedTrue(Book book);
}
