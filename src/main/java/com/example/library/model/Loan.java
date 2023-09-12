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

	private Date borrowingDate;
	private int borrowingPeriod; //in days
	private boolean isBorrowed;
	
	//one-to-many relationship between Book and Loan -> each book can have multiple loans (including both active and returned loans).
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Loan() {
		
	}

	public Loan(Date borrowingDate, int borrowingPeriod, boolean isBorrowed) {

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", borrowingDate=" + borrowingDate + ", borrowingPeriod=" + borrowingPeriod
				+ ", isBorrowed=" + isBorrowed + ", book=" + book + ", user=" + user + "]";
	}

}
