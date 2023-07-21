package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
