package com.example.library.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="loans")
public class Loan {

	private @Id @GeneratedValue Long id;
	private String borrowedBy;
	private Date borrowingDate;
	private int borrowingPeriod; //in days
	private boolean isBorrowed;
	
	//one-to-many relationship between Book and Loan -> each book can have multiple loans (including both active and returned loans).
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	public Loan() {
		
	}

	public Loan(String borrowedBy, Date borrowingDate, int borrowingPeriod, boolean isBorrowed) {

		this.borrowedBy = borrowedBy;
		this.borrowingDate = borrowingDate;
		this.borrowingPeriod = borrowingPeriod;
		this.isBorrowed = isBorrowed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(String borrowedBy) {
		this.borrowedBy = borrowedBy;
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}
	
	public int getBorrowingPeriod() {
		return borrowingPeriod;
	}

	public void setBorrowingPeriod(int borrowingPeriod) {
		this.borrowingPeriod = borrowingPeriod;
	}

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", borrowedBy=" + borrowedBy + ", borrowingDate=" + borrowingDate
				+ ", borrowingPeriod=" + borrowingPeriod + ", isBorrowed=" + isBorrowed + ", book=" + book + "]";
	}

}
