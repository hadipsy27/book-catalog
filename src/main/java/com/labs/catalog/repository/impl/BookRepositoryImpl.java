package com.labs.catalog.repository.impl;

import com.labs.catalog.domain.Book;
import com.labs.catalog.repository.BookRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Data
public class BookRepositoryImpl implements BookRepository {

    private Map<Long, Book> bookMap;

    @Override
    public Book findBookById(Long id) {
        Book book = bookMap.get(id);
        return book;
    }
}
