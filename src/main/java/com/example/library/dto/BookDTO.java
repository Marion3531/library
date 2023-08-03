package com.example.library.dto;

import java.util.List;

import com.example.library.model.Author;

public class BookDTO {

	private String title;
	private String description;
	private List<Author> authors;
	private boolean isBorrowed;
	
	public BookDTO(String title, String description, List<Author> authors, boolean isBorrowed) {
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.isBorrowed = isBorrowed;
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
		
}
