package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Comment;
import com.example.library.service.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

	private final CommentService service;

	public CommentController(CommentService service) {
		
		this.service = service;
	}

	@GetMapping("/comments")
	public List<Comment> getAll(@RequestParam(required = false) Long bookId) {
		
		if (bookId != null) {
			return service.getCommentsByBookId(bookId);
			
		} else {
			return service.getAllComments();
		}
	}

	@GetMapping("/comments/{id}")
	public Comment getById(@PathVariable Long id) {

		return service.getCommentbyId(id);
	}

	@PostMapping("/comments/{bookId}")
	public Comment newComment(@PathVariable Long bookId, @RequestBody String content) {

		Long userId = 305L; // id par défaut pour l'instant

		return service.createComment(userId, bookId, content);
	}

	@PutMapping("/comments/{id}")
	public Comment updateComment(@PathVariable Long id, @RequestBody String content) {

		return service.updateComment(id, content);
	}

	@DeleteMapping("/comments/{id}")
	public void deleteComment(@PathVariable Long id) {

		service.deleteComment(id);
	}
}
