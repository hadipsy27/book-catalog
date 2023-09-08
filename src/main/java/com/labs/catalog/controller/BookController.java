package com.labs.catalog.controller;

import com.labs.catalog.dto.BookCreateDTO;
import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public String findBookList(Model model){
        List<BookDetailDto> books = bookService.findBookDetail();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/new")
    public String loadBookForm(Model model) {
        BookCreateDTO bookCreateDTO = new BookCreateDTO();
        model.addAttribute("bookCreateDTO", bookCreateDTO);
        return "book/book-form";
    }

    @PostMapping("/new")
    public String addNewBook(@ModelAttribute("BookCreateDTO") BookCreateDTO dto, Model model) {
        bookService.createNewBook(dto);
        return "redirect:/book/list";
    }
}
