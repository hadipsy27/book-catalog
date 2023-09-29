package com.labs.catalog.service;

import com.labs.catalog.dto.*;

import java.util.List;


public interface BookService {

    public BookDetailResponseDTO findBookDetailById(String secureId);

    public List<BookDetailResponseDTO> findBookDetail();

    public void createNewBook(BookCreateDTO dto);

    public void updateBook(Long bookId, BookUpdateRequestDTO dto);

    public void deleteBook(Long bookId);

    ResultPageResponseDTO<BookListResponseDTO> findBookList(Integer page, Integer limit, String sortBy, String direction,
                                                            String publisherName, String bookTitle, String authorName);
}
