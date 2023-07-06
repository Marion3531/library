package com.example.library.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="books")
public class Book {

	private @Id @GeneratedValue Long id;
	private String title;
	private String description;
	private Author author;
	
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();
	
	Book() {}
	
	public Book(String title, String description){
		this.title = title;
		this.description = description;
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
	
	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", authors=" + authors + "]";
	}
	
}
