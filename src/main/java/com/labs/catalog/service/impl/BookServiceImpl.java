package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Book;
import com.labs.catalog.dto.BookDetailDto;
import com.labs.catalog.repository.BookRepository;
import com.labs.catalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
