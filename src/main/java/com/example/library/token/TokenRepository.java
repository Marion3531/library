package com.example.library.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	
	//get all the valid tokens for a specific user
	@Query("SELECT t from Token t INNER JOIN User u on t.user.id = u.id "
			+ "WHERE u.id = :userId AND (t.expired = false OR t.revoked = false)")
	List<Token> findAllValidTokensByUser(Long userId);
	
	Optional<Token> findByToken (String token);
	
}
