package com.example.orderservice.mapper;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.getNewArrival(),
                product.getTags(),
                product.getDescription()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getImageUrl(),
                productDto.getNewArrival(),
                productDto.getTags(),
                productDto.getDescription()
        );
    }
}
