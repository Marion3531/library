/*package com.example.library.other;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import com.example.library.repository.AuthorRepository;

@Configuration
class LoadDatabase {
	
	//private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	  @Bean
	  CommandLineRunner initDatabase(BookRepository bookRepository, AuthorRepository authorRepository, UserRepository userRepository) {

	    return args -> {
	      bookRepository.save(new Book("Hot Milk", "Sofia, a young anthropologist, has spent much of her life trying to solve the mystery of her mother's unexplainable illness."));
	      bookRepository.save(new Book("The Cost of Living", "Levy considers what it means to live with meaning, value, and pleasure, to seize the ultimate freedom of writing our own lives, and reflects on the work of such artists and thinkers as Simone de Beauvoir, Elena Ferrante, David Lynch, and Emily Dickinson."));
	      bookRepository.save(new Book("White Teeth", "At the center of this invigorating novel are two unlikely friends, Archie Jones and Samad Iqbal. Hapless veterans of World War II, Archie and Samad and their families become agents of England’s irrevocable transformation."));
	      bookRepository.save(new Book("Watchmen", "A murder mystery-turned-nationwide conspiracy, WATCHMEN examines the lives of the eponymous superhero team as they seem to decay alongside the ever-darkening America around them."));

	      //bookRepository.findAll().forEach(book -> log.info("Preloaded " + book));

	      authorRepository.save(new Author("Deborah", "Levy"));
	      authorRepository.save(new Author("Zadie", "Smith"));
	      authorRepository.save(new Author("Alan", "Moore"));
	      authorRepository.save(new Author("Dave", "Gibson"));

	      //problem with hibernate lazy fetch
	      authorRepository.findAll().forEach(author -> {
	        log.info("Preloaded " + author);
	      });
	      
	      userRepository.save(new User("Martha31", "martha.dufour@gmail.com"));
	      userRepository.save(new User("Toto_87", "thierry.cazanovas@gmail.com"));
	      userRepository.save(new User("Bert00", "bertha.schmidt@gmail.com"));
	        
	    };
	  }*/

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
  }	
  	
 }*/
 

 