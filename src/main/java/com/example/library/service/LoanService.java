package com.example.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.library.exception.BookAlreadyBorrowedException;
import com.example.library.exception.LoanNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanService {

	private LoanRepository repository;
	private BookService bookService;
	private UserService userService;
	
	public LoanService(LoanRepository repository, BookService bookService, UserService userService) {
		this.repository = repository;
		this.bookService = bookService;
		this.userService = userService;
	}
	
	//GET ALL
	public List<Loan> getAllLoans(){
		
		List<Loan> loans = repository.findAll();
		
		return loans;
	}
	
	public Loan getLoanByid(Long loanId) {
		
		Loan loan = repository.findById(loanId).orElseThrow(() -> new LoanNotFoundException(loanId));
		
		return loan;
	}
	
	//POST -> create a loan/borrow a book
	public Loan createLoan(Long bookId, User user) {
		
		Book book = bookService.getBookById(bookId);
		
		List<Loan> activeLoans = repository.findByBookAndIsBorrowedTrue(book);
		
		if(activeLoans.isEmpty()) {
			Loan newLoan = new Loan();
			newLoan.setBook(book);
			newLoan.setUser(user);
			newLoan.setBorrowingDate(new Date());
			newLoan.setBorrowingPeriod(15);
			newLoan.setBorrowed(true);
			
			return repository.save(newLoan);
		}else{
			throw new BookAlreadyBorrowedException(bookId);
		}
	
	}
	
	//PUT -> update loan to isBorrowed = false/return the book
	@Transactional
	public void updateLoanToFalse(Long loanId) {
		
		repository.markBookAsReturned(loanId);
	}
	
	public void deleteLoanFromDB(Long loanId) {
		
		repository.deleteById(loanId);
	}
	
}
