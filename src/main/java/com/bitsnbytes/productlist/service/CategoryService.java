package com.bitsnbytes.productlist.service;

import com.bitsnbytes.productlist.dto.CategoryDTO;
import com.bitsnbytes.productlist.entity.Category;
import com.bitsnbytes.productlist.mapper.CategoryMapper;
import com.bitsnbytes.productlist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }
    // Get Category by Id
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }
    // Delete Category
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category "+id+" has been deleted successfully";
    }
}
