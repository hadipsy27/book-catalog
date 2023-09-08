package com.labs.catalog.service;

import com.labs.catalog.dto.BookDetailDto;

import java.util.List;


public interface BookService {

    public BookDetailDto findBookDetailById(Long id);

    public List<BookDetailDto> findBookDetail();
}
