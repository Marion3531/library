package com.example.library.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String get() {
        return "GET:: admin controller";
    }
    
    @PostMapping("/admin")
    public String post() {
        return "POST:: admin controller";
    }
    
    @PutMapping("/admin")
    public String put() {
        return "PUT:: admin controller";
    }
    
    @DeleteMapping("/admin")
    public String delete() {
        return "DELETE:: admin controller";
    }
}
