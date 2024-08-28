package com.example.library.controller.altControllers;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AltBookController {

    private final BookService bookService;

    public AltBookController(BookService bookService) {
        this.bookService = bookService;
    }

    //book catalog where all the books are
    @GetMapping("/alt-books")
    public String book(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "catalog"; //name of the view
    }

    //returns the information page for a specific book
    @GetMapping("/alt-book-info")
    public String bookInfo(Model model) {
        return "book-info";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        return "add-book";
    }
}

