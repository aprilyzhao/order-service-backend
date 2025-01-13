package com.example.orderservice.service;

import com.example.orderservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductByid(Long productid);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long productid, ProductDto updatedProduct);

    void deleteProduct(Long productid);
}
