package com.example.library.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Loan;
import com.example.library.service.LoanService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoanController {

	private LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/loans")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Loan> getAllLoans(){
		
		return loanService.getAllLoans();
	}
	
	@GetMapping("/loans/{loanId}")
	@PreAuthorize("hasRole('ADMIN')")
	public Loan getLoanbyId(@PathVariable Long loanId) {
		
		return loanService.getLoanByid(loanId);
	}
	
	@DeleteMapping("/loans/{loanId}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteLoanFromDb(@PathVariable Long loanId) {
		
		loanService.deleteLoanFromDB(loanId);
	}
}
