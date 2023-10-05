package com.labs.catalog.repository;

import com.labs.catalog.domain.Book;
import com.labs.catalog.dto.BookQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findById(Long id);

    // SQL  : SELECT b FROM book b where b.secure_id = id;
    // JPQL : SELECT b FROM Book b where b.secureId = :id;
    Optional<Book> findBySecureId(String secureId);

    // SQL : SELECT b FROM Book b INNER JOIN Publisher p ON p.id = b.publisher_id WHERE p.name = :publisherName AND b.title = :bookTitle;
    @Query(value = "SELECT DISTINCT new com.labs.catalog.dto.BookQueryDTO(b.id, b.secureId, b.title, p.name, b.description) " +
            "FROM Book b " +
            "INNER JOIN Publisher p ON p.id = b.publisher.id " +
            "INNER JOIN b.authors ba " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%')) " +
            "AND LOWER(p.name) LIKE LOWER(CONCAT('%', :publisherName, '%')) " +
            "AND LOWER(ba.name) LIKE LOWER(CONCAT('%', :authorName, '%'))" )
    Page<BookQueryDTO> findBookList(String publisherName, String bookTitle, String authorName, Pageable pageable);

//    public List<Book> findAll();
//
//    public void save(Book book);
//
//    public void update(Book book);
//
//    public void delete(Long bookId);

}
