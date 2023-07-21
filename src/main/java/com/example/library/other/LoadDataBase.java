package com.example.library.other;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;

@Configuration
class LoadDatabase {
	
	//private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	  @Bean
	  CommandLineRunner initDatabase(BookRepository bookRepository, AuthorRepository authorRepository) {

	    return args -> {
	      bookRepository.save(new Book("Hot Milk", "xxx"));
	      bookRepository.save(new Book("The Cost of Living", "xxx"));
	      bookRepository.save(new Book("White Teeth", "xxx"));
	      bookRepository.save(new Book("Watchmen", "xxx"));

	      //bookRepository.findAll().forEach(book -> log.info("Preloaded " + book));

	      authorRepository.save(new Author("Deborah", "Levy"));
	      authorRepository.save(new Author("Zadie", "Smith"));
	      authorRepository.save(new Author("Alan", "Moore"));
	      authorRepository.save(new Author("Dave", "Gibson"));

	      //problem with hibernate lazy fetch
	      /*authorRepository.findAll().forEach(author -> {
	        log.info("Preloaded " + author);
	      });*/
	      
	    };
	  }

  /*private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(BookRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Book("Hot Milk")));
      log.info("Preloading " + repository.save(new Book("White Teeth")));
    };
  }
  
  @Bean
  CommandLineRunner initDatabase(AuthorRepository repository) {

    return args -> {
    	log.info("Preloading " + repository.save(new Author("Deborah", "Levy")));
    	log.info("Preloading " + repository.save(new Author("Zadie", "Smith")));
    };
  }	*/
  	
 }

 