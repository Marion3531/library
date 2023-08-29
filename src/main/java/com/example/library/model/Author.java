package com.example.library.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="authors")
public class Author {
	
	private @Id @GeneratedValue Long id;
	String firstname;
	String lastname;
    
	@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
	
	@JsonIgnoreProperties(value = "authors") 
	private List<Book> books = new ArrayList<>();
	
	/* @JsonIgnoreProperties(value = "authors") :
	 * for each book, you can fetch the authors, but inside the authors ignore the books property cause we don't really need it. 
	 * we started from book and we only want to see the authors, we don't need to go further*/
	
	public Author() {}
	
	public Author(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", books=" + books + "]";
	}

}
