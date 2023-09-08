package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Book;
import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.repository.BookRepository;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDetailDto findBookDetailById(Long id) {
        Book book = bookRepository.findBookById(id);
        BookDetailDto bookDetailDto = new BookDetailDto();

        bookDetailDto.setBookId(book.getId());
        bookDetailDto.setAuthorName(book.getAuthor().getName());
        bookDetailDto.setBookTitle(book.getTitle());
        bookDetailDto.setBookDescription(book.getDescription());
        return bookDetailDto;
    }

    @Override
    public List<BookDetailDto> findBookDetail() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> {
            BookDetailDto bookDetailDto = new BookDetailDto();
            bookDetailDto.setAuthorName(book.getAuthor().getName());
            bookDetailDto.setBookTitle(book.getTitle());
            bookDetailDto.setBookDescription(book.getDescription());
            bookDetailDto.setBookId(book.getId());
            return bookDetailDto;
        }).collect(Collectors.toList());
    }
}
