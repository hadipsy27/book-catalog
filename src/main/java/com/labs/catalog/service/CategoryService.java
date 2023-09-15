package com.labs.catalog.service;


import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;

public interface CategoryService {

    public void createAndUpdateCategory(CategoryCreateAndUpdateRequestDTO dto);
}
