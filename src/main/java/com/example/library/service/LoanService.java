package com.example.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.repository.LoanRepository;

public class LoanService {

	private LoanRepository repository;
	
	@Autowired
	private BookService bookService;
	
	//GET ALL
	public List<Loan> getAllLoans(){
		
		List<Loan> loans = repository.findAll();
		
		return loans;
	}
	
	//POST -> create a loan/borrow a book
	public Loan createLoan(Long bookId, Loan loan) {
		
		Book book = bookService.getBookById(bookId);
		
		Loan newLoan = new Loan();
		newLoan.setBook(book);
		newLoan.setBorrowedBy(loan.getBorrowedBy());
		newLoan.setBorrowingDate(new Date());
		newLoan.setBorrowingPeriod(15);
		newLoan.setBorrowed(true);
		
		return repository.save(newLoan);
	}
	
	//DELETE -> delete loan/return the book
	public void deleteLoan(Long id) {
		
		repository.deleteById(id);
	}
	
}
