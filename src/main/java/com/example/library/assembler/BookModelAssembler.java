package com.example.library.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.library.controller.BookController;
import com.example.library.model.Book;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

	  @Override
	  public EntityModel<Book> toModel(Book book) {

	    return EntityModel.of(book, //
	        linkTo(methodOn(BookController.class).one(book.getId())).withSelfRel(),
	        linkTo(methodOn(BookController.class).all()).withRel("books"));
	  }
}
