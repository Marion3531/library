package com.example.library.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleUserController {

    @GetMapping("/simpleUser")
    public String get() {
        return "GET:: user controller";
    }
    
    @PostMapping("/simpleUser")
    public String post() {
        return "POST:: user controller";
    }
    
    @PutMapping("/simpleUser")
    public String put() {
        return "PUT:: user controller";
    }
    
    @DeleteMapping("/simpleUser")
    public String delete() {
        return "DELETE:: user controller";
    }
}
