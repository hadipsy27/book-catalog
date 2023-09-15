package com.labs.catalog.web;

import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;
import com.labs.catalog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @PostMapping("/v1/category")
    public ResponseEntity<Void> createAndUpdateCategory(@RequestBody CategoryCreateAndUpdateRequestDTO dto) {
        categoryService.createAndUpdateCategory(dto);
        return ResponseEntity.created(URI.create("/v1/category")).build();
    }
}
