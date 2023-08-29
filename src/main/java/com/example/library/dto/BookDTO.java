package com.example.library.dto;

import java.io.Serializable;
import java.util.List;

import com.example.library.model.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO implements Serializable {
	
    private Long id;

	private String title;
	private String description;

    @JsonManagedReference
	private List<Author> authors;

    private List<AuthorDTO> authorDtos;
    
	private boolean isBorrowed;

	public BookDTO() {
		
	}
	
    public BookDTO(Long id, String title, List<AuthorDTO> authorDtos, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.authorDtos = authorDtos;
        this.isBorrowed = isBorrowed;
    }
    
    public BookDTO(Long id, String title, String description, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }
	
	public BookDTO(Long id, String title, String description, List<Author> authors, boolean isBorrowed) {
        this.id = id;
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.isBorrowed = isBorrowed;
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

    public List<AuthorDTO> getAuthorDtos() {
        return authorDtos;
    }

    public void setAuthorDtos(List<AuthorDTO> authorDtos) {
        this.authorDtos = authorDtos;
    }

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
		
}