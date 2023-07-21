package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Loan;
import com.example.library.service.LoanService;

@RestController
public class LoanController {

	private LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/loans")
	public List<Loan> getAllLoans(){
		
		return loanService.getAllLoans();
	}
}
