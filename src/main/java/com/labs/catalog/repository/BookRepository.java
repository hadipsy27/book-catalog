package com.labs.catalog.repository;

import com.labs.catalog.domain.Book;

public interface BookRepository {
    public Book findBookById(Long id);
}
