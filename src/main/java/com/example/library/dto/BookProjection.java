package com.example.library.dto;

import java.util.List;
import org.springframework.data.rest.core.config.Projection; //!mettre la dependency spring-boot-starter-data-rest dans pom.xml sinon bug

import com.example.library.model.Author;
import com.example.library.model.Book;

@Projection(name = "customBook", types = { Book.class })
public interface BookProjection {

	String getTitle();

	String getDescription();

	List<Author> getAuthors();

	Boolean getIsBorrowed(); 

}
