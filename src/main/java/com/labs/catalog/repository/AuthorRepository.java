package com.labs.catalog.repository;

import com.labs.catalog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Method name Conversion
    // find+keyword
    // sql -> Select * from Author a where a.id = :authorId
    Optional<Author> findById(Long id);

    // sql -> Select a from Author a where a.name LIKE :authorName
    public List<Author> findByNameLike(String authorName);

}
