package com.labs.catalog.repository;

import com.labs.catalog.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByCode(String code);

    Page<Category> findByNameLikeIgnoreCase(String categoryname, Pageable pageable);

    List<Category> findByCodeIn(List<String> codes);
}
