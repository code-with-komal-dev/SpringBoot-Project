package com.bitsnbytes.productlist.mapper;

import com.bitsnbytes.productlist.dto.ProductDTO;
import com.bitsnbytes.productlist.entity.Category;
import com.bitsnbytes.productlist.entity.Product;

public class ProductMapper {

    //Product entity to productDTO
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory().getId()
        );
    }
    //ProductDTO to Product entity
    public static Product toProductEntity(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
