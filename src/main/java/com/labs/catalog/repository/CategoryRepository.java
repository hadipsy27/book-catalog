package com.labs.catalog.repository;

import com.labs.catalog.domain.Category;
import com.labs.catalog.dto.CategoryQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByCode(String code);

    Page<Category> findByNameLikeIgnoreCase(String categoryname, Pageable pageable);

    List<Category> findByCodeIn(List<String> codes);

    @Query("SELECT new com.labs.catalog.dto.CategoryQueryDTO(b.id, bc.code) " +
            "FROM Book b " +
            "JOIN b.categories bc " +
            "WHERE b.id IN :bookIdList")
    List<CategoryQueryDTO> findCategoryByBookIdLIst(List<Long> bookIdList);
}
