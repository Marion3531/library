package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.library.model.Book;
import com.example.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

	List<Loan> findByBookAndIsBorrowedTrue(Book book);
	
	@Modifying
	@Query("UPDATE Loan l SET l.isBorrowed = false WHERE l.id = :loanId")
	void markBookAsReturned(Long loanId);
}
