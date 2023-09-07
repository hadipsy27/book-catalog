package com.labs.catalog.web;

import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BookResource {

    private final BookService bookService;

    @GetMapping("/book/{bookId}")
    public BookDetailDto findBookDetail(@PathVariable("bookId") Long id){
        return bookService.findBookDetailById(id);
    }
}
