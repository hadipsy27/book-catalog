package com.labs.catalog.controller;

import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public String findBookList(Model model){
        List<BookDetailDto> books = bookService.findBookDetail();
        model.addAttribute("books", books);
        return "book/list";
    }
}
