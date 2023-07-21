package com.example.library.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.assembler.BookModelAssembler;
import com.example.library.assembler.LoanModelAssembler;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;

@RestController
public class BookController {

	private final BookService bookService;
	private final BookModelAssembler assembler;
	private final BookRepository repository;

	BookController(BookService bookService, BookModelAssembler assembler, BookRepository repository) {
		this.bookService = bookService;
		this.assembler = assembler;
		this.repository = repository;
	}
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private LoanModelAssembler loanAssembler;
	
	// Aggregate root
	@GetMapping("/books")
	public CollectionModel<EntityModel<Book>> all() {

	    List<Book> books = bookService.getAllBooks(); //get the list of books from bookService
	    
	  //convert each book into an EntityModel and collect them in a list
	    List<EntityModel<Book>> bookModels = books.stream() 
	            .map(assembler::toModel) //for each book in the stream, the method toModel() of the object assembler is called to convert it into an EntityModel<Book>.
	            .collect(Collectors.toList()); //Collectors.toList() est un collecteur prédéfini qui collecte les éléments dans une liste.

	    return CollectionModel.of(bookModels, linkTo(methodOn(BookController.class).all()).withSelfRel());
	}
    
    //Single Item
    @GetMapping("/books/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {
    	
    	Book book = bookService.getBookById(id);
    	
        return assembler.toModel(book);
    }
    
    //SEARCH(GET)
    @GetMapping("/books/search")
    public CollectionModel<EntityModel<Book>> searchBook(@RequestParam("query") String query) {
    	
        List<Book> booksFound = bookService.searchBookByTitle(query);

        List<EntityModel<Book>> bookModels = booksFound.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        System.out.println("bonjour");
        return CollectionModel.of(bookModels, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }
    
    //BORROW(POST)
    @PostMapping("books/borrow/{id}")
    public ResponseEntity<?> borrowBook(@PathVariable Long id, @RequestBody Loan newLoan){
    	
    	Book book = bookService.getBookById(id); //récup book by id
    	
    	newLoan.setBook(book); //attribution du book au prêt
    	
    	Loan loan = loanService.createLoan(id, newLoan); 
    	
    	EntityModel<Loan> entityModel = loanAssembler.toModel(loanRepository.save(newLoan));
    	
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(loan);
    
    }
    
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book newBook) {
    	
        Book book = bookService.createNewBook(newBook);
        
        EntityModel<Book> entityModel = assembler.toModel(repository.save(newBook));
        
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(book);
    }
   
    
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book){
    	
        Book updatedBook = bookService.replaceBook(id, book);
        
    	EntityModel<Book> entityModel = assembler.toModel(updatedBook);
    	
        return ResponseEntity.ok(entityModel);
    }
    
    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id){
    	
    	bookService.deleteBookById(id);
    	
    	return ResponseEntity.noContent().build();
    }

}
