package com.example.orderservice.service.impl;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entity.Product;
import com.example.orderservice.entity.Tag;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.mapper.ProductMapper;
import com.example.orderservice.repository.ProductRepository;
import com.example.orderservice.repository.TagRepository;
import com.example.orderservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    @Override
    public ProductDto createProduct( ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }
    private Tag createTag(String tagName) {
        Tag newTag = new Tag();
        newTag.setName(tagName);
        return tagRepository.save(newTag);
    }

    @Override
    public ProductDto getProductByid(Long productid) {
        Product product = productRepository.findById(productid)
                .orElseThrow(()->new ResourceNotFoundException("Product is not exists with given id." + productid));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product)->ProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productid, ProductDto updatedProduct) {
        Product product = productRepository.findById(productid).orElseThrow(()-> new ResourceNotFoundException("Product is not exists with given id." + productid));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setNewArrival(updatedProduct.getNewArrival());
        product.setDescription(updatedProduct.getDescription());
        product.setTags(updatedProduct.getTags());

        Product updatedProductObj = productRepository.save(product);

        return ProductMapper.mapToProductDto(updatedProductObj);
    }


    @Override
    public void deleteProduct(Long productid) {

        Product product = productRepository.findById(productid).orElseThrow(()-> new ResourceNotFoundException("Product is not exists with given id." + productid));
        productRepository.deleteById(productid);
    }
}
