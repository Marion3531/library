package com.example.library.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name="books")
public class Book {

	private @Id @GeneratedValue Long id;
	private String title;
	private String description;
	private Integer yearOfPublication;
	private Integer numberOfPages;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "author_book",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties(value = "books")
    private List<Author> authors = new ArrayList<>();
    
    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties(value = "book")
    private List<Loan> loans = new ArrayList<>();
    
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();
	
	public Book() {}
	
	public Book(String title, String description, Integer yearOfPublication, Integer numberOfPages){
		this.title = title;
		this.description = description;
		this.yearOfPublication = yearOfPublication;
		this.numberOfPages = numberOfPages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(Integer yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", yearOfPublication="
				+ yearOfPublication + ", numberOfPages=" + numberOfPages + ", authors=" + authors + ", loans=" + loans
				+ ", comments=" + comments + "]";
	}

}
