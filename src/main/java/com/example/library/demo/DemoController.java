package com.example.library.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
@CrossOrigin(origins = "http://localhost:3000")
public class DemoController {

	@GetMapping
	public ResponseEntity<String> hello() {
		
		return ResponseEntity.ok("hello");
	}
}
 