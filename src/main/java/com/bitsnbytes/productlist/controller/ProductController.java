package com.bitsnbytes.productlist.controller;

import com.bitsnbytes.productlist.dto.ProductDTO;
import com.bitsnbytes.productlist.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    // get All Products
    @GetMapping
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProducts();
    }

    // create Product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct =  productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    // update Product
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }
    // get Product By Id
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    // delete Product
    @DeleteMapping("/{id}")
        public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
