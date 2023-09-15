package com.labs.catalog.service;


import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;
import com.labs.catalog.dto.CategoryListResponseDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;

public interface CategoryService {

    public void createAndUpdateCategory(CategoryCreateAndUpdateRequestDTO dto);

    ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages,
                                                                    Integer limit,
                                                                    String sortBy,
                                                                    String sortDirection,
                                                                    String categoryName);
}
