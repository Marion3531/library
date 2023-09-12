package com.example.library.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	private @Id @GeneratedValue Long id;
	private Date date;
	private String content; 
	
	@ManyToOne
    @JoinColumn(name = "book_id")
	@JsonIgnoreProperties(value="comments")
    private Book book;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnoreProperties(value="comments")
    private User user;
	
	public Comment() {
		
	}
	
	public Comment(Date date, String content) {
		this.date = date;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Comment [id=" + id + ", date=" + date + ", content=" + content + "]";
	}
	
}
