package com.labs.catalog.service;

import com.labs.catalog.dto.BookDetailDto;


public interface BookService {

    public BookDetailDto findBookDetailById(Long id);
}
