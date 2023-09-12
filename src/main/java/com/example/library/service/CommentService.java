package com.example.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.CommentNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Comment;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CommentRepository;

@Service
public class CommentService {
	
	private final CommentRepository repository;
	private final BookRepository bookRepository;
	
	public CommentService(CommentRepository repository, BookRepository bookRepository) {
		this.repository = repository;
		this.bookRepository = bookRepository;
	}
	
	public List<Comment> getAllComments() {
		
		return repository.findAll();
	}
	
	public Comment getCommentbyId(Long id) {
		
		Comment comment = repository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
		
		return comment;
	}
	
	public List<Comment> getCommentsByBookId(Long bookId) {
		
		return repository.findByBookId(bookId);
	}
	
	public Comment createComment(Long bookId, String content, User user) {
		
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
		
		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setContent(content);
		comment.setBook(book);
		comment.setUser(user);
		
		return repository.save(comment);
	}
	
	public Comment updateComment(Long id, String content) {
		
		Comment comment = repository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
		
		comment.setContent(content);
		
		return repository.save(comment);
	}
	
	public void deleteComment(Long id) {
		
		repository.deleteById(id);
	}
}
