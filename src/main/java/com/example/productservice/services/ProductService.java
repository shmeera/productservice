package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id);

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto createProduct(GenericProductDto request);

    public List<GenericProductDto> LimitedResults(Long limit);

    public GenericProductDto DeleteProduct(Long Id);
    public  GenericProductDto UpdateProduct(Long Id);
}
