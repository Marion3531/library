package com.example.library.dto;

import java.util.List;

public interface BookTitleAuthors {
	Long getId();

	String getTitle();

	List<AuthorName> getAuthors();
}