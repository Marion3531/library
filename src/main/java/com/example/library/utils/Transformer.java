package com.example.library.utils;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.AuthorName;
import com.example.library.dto.BookAvailability;
import com.example.library.dto.BookDTO;

public class Transformer {

    private Transformer () {}

    public static AuthorDTO toDto(AuthorName author) {
        return new AuthorDTO(
            author.getFirstname(),
            author.getLastname()
        );
    }

    public static BookDTO toDto(BookAvailability bookAvailability) {
        return new BookDTO(
            bookAvailability.getBook().getId(),
            bookAvailability.getBook().getTitle(),
            bookAvailability.getBook().getAuthors()
                .stream()
                .map(Transformer::toDto)
                .toList(),
            bookAvailability.getIsBorrowed()
        );
    }
}
