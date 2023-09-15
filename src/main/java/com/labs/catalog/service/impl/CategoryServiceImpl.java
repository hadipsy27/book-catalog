package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Category;
import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;
import com.labs.catalog.repository.CategoryRepository;
import com.labs.catalog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public void createAndUpdateCategory(CategoryCreateAndUpdateRequestDTO dto) {
        final Category category = categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
        if (category.getCode() == null){
            category.setCode(dto.getCode().toLowerCase()); // New data
        }
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        categoryRepository.save(category);
    }
}
