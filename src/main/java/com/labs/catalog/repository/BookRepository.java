package com.labs.catalog.repository;

import com.labs.catalog.domain.Book;
import com.labs.catalog.dto.BookDetailDto;

import java.util.List;

public interface BookRepository {
    public Book findBookById(Long id);

    public List<Book> findAll();

    public void save(Book book);

}
