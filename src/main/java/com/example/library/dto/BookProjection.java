package com.example.library.dto;

import java.util.List;
//import org.springframework.data.rest.core.config.Projection; 
//!mettre la dependency spring-boot-starter-data-rest dans pom.xml sinon bug
//!!NON -> DISAPPEARED IDs GATE

import com.example.library.model.Author;

public interface BookProjection {

	String getTitle();

	String getDescription();

	List<Author> getAuthors();

	Boolean getIsBorrowed(); 

}
