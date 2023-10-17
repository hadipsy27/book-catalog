package com.labs.catalog.web;

import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;
import com.labs.catalog.dto.CategoryCreateUpdateRecordDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;
import com.labs.catalog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CategoryResource {

    private final CategoryService categoryService;

    @PostMapping("/v1/category")
    public ResponseEntity<Void> createAndUpdateCategory(@RequestBody CategoryCreateUpdateRecordDTO dto) {
        categoryService.createAndUpdateCategory(dto);
        return ResponseEntity.created(URI.create("/v1/category")).build();
    }

    @GetMapping("/v1/category")
    public ResponseEntity<ResultPageResponseDTO> findCategoryList(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "categoryName", required = false) String categoryName) {
        return ResponseEntity.ok().body(categoryService.findCategoryList(pages, limit, sortBy, direction, categoryName));
    }
}
