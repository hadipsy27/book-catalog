package com.labs.catalog.service;


import com.labs.catalog.domain.Category;
import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;
import com.labs.catalog.dto.CategoryCreateUpdateRecordDTO;
import com.labs.catalog.dto.CategoryListResponseDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    public void createAndUpdateCategory(CategoryCreateUpdateRecordDTO dto);

    ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages,
                                                                    Integer limit,
                                                                    String sortBy,
                                                                    String sortDirection,
                                                                    String categoryName);

    List<Category> findCategories(List<String> categoryCodeList);

    List<CategoryListResponseDTO> constructDTO(List<Category> categories);

    Map<Long, List<String>> findCategoriesMap(List<Long> bookIdList);
}
