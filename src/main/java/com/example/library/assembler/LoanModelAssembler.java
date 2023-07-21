package com.example.library.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.library.controller.AuthorController;
import com.example.library.model.Loan;

@Component
public class LoanModelAssembler implements RepresentationModelAssembler <Loan, EntityModel<Loan>> {

	@Override
	public EntityModel<Loan> toModel(Loan loan) {
	    return EntityModel.of(loan, //
	            linkTo(methodOn(AuthorController.class).one(loan.getId())).withSelfRel(),
	            linkTo(methodOn(AuthorController.class).all()).withRel("authors"));
	}

}
