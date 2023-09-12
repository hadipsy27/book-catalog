package com.labs.catalog.repository;

import com.labs.catalog.domain.Book;
import com.labs.catalog.dto.BookDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findById(Long id);

//    public List<Book> findAll();
//
//    public void save(Book book);
//
//    public void update(Book book);
//
//    public void delete(Long bookId);

}
