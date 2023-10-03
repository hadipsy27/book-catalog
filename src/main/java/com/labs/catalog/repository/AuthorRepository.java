package com.labs.catalog.repository;

import com.labs.catalog.domain.Author;
import com.labs.catalog.dto.AuthorQueryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Method name Conversion
    // find+keyword
    // sql -> Select * from Author a where a.id = :authorId
//    Optional<Author> findById(Long id);

    public Optional<Author> findBySecureId(String secureId);

    List<Author> findBySecureIdIn(List<String> secureId);

    Optional<Author> findByIdAndDeletedFalse(Long id);

    // sql -> Select a from Author a where a.name LIKE :authorName
    public List<Author> findByNameLike(String authorName);

    @Query("SELECT new com.labs.catalog.dto.AuthorQueryDTO(b.id, ba.name) " +
            "FROM Book b " +
            "JOIN b.authors ba " +
            "WHERE b.id IN :bookIdList")
    List<AuthorQueryDTO> findAuthorsByBookIdList(List<Long> bookIdList);

}
