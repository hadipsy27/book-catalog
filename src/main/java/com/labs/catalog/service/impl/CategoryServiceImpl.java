package com.labs.catalog.service.impl;

import com.labs.catalog.domain.Category;
import com.labs.catalog.dto.CategoryCreateAndUpdateRequestDTO;
import com.labs.catalog.dto.CategoryListResponseDTO;
import com.labs.catalog.dto.ResultPageResponseDTO;
import com.labs.catalog.repository.CategoryRepository;
import com.labs.catalog.service.CategoryService;
import com.labs.catalog.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy, String sortDirection, String categoryName) {

        categoryName = StringUtils.isEmpty(categoryName) ? "%" : categoryName + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(sortDirection), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        final Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);

        final List<CategoryListResponseDTO> categories = pageResult.stream().map((category -> {
            CategoryListResponseDTO listResponseDTO = new CategoryListResponseDTO();
            listResponseDTO.setCode(category.getCode());
            listResponseDTO.setName(category.getName());
            listResponseDTO.setDescription(category.getDescription());

            return listResponseDTO;

        })).collect(Collectors.toList());
        return PaginationUtil.createResultPageDTO(categories, pageResult.getTotalElements(), pageResult.getTotalPages());
    }
}
