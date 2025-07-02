package com.bitsnbytes.productlist.service;

import com.bitsnbytes.productlist.dto.ProductDTO;
import com.bitsnbytes.productlist.entity.Category;
import com.bitsnbytes.productlist.entity.Product;
import com.bitsnbytes.productlist.exception.CategoryNotFoundException;
import com.bitsnbytes.productlist.mapper.ProductMapper;
import com.bitsnbytes.productlist.repository.CategoryRepository;
import com.bitsnbytes.productlist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        /**
         * name, description, price, categoryId
         */
        System.out.println("CategoryId: " + productDTO.getCategoryId());
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category Id "+productDTO.getCategoryId()+" not found"));
        // DTO -> Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);
        // Entity -> DTO
        return ProductMapper.toProductDTO(product);
    }

    // Get All Products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    // Get Product by Id
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
    }

    // Update Product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted successfully";
    }

}
