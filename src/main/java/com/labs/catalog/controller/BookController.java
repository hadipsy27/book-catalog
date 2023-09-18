package com.labs.catalog.controller;

import com.labs.catalog.dto.BookCreateDTO;
import com.labs.catalog.dto.BookDetailResponseDTO;
import com.labs.catalog.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
        List<BookDetailResponseDTO> books = bookService.findBookDetail();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/new")
    public String loadBookForm(Model model) {
        BookCreateDTO bookCreateRequestDTO = new BookCreateDTO();
        model.addAttribute("bookCreateDTO", bookCreateRequestDTO);
        return "book/book-form";
    }

    @PostMapping("/new")
    public String addNewBook(@ModelAttribute("bookCreateDTO") @Valid BookCreateDTO dto,
                             BindingResult bindingResult,
                             Errors errors,
                             Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("bookCreateDTO", dto);
            return "book/book-form";
        }
        bookService.createNewBook(dto);
        return "redirect:/book/list";
    }
}
