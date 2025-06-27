package com.bitsnbytes.productlist.service;

import com.bitsnbytes.productlist.dto.CategoryDTO;
import com.bitsnbytes.productlist.entity.Category;
import com.bitsnbytes.productlist.mapper.CategoryMapper;
import com.bitsnbytes.productlist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    // Create Category
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
    // Get All Categories
    // Get Category by Id
    // Delete Category
}
