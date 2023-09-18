package com.labs.catalog.repository;

import com.labs.catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findById(Long id);

    Optional<Book> findBySecureId(String secureId);

//    public List<Book> findAll();
//
//    public void save(Book book);
//
//    public void update(Book book);
//
//    public void delete(Long bookId);

}
