package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        System.out.println("calling from GenericProductDto");
        throw new NotFoundException("fake call");
        //return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto request) {
        return null;
    }
//
//    @Override
//    public List<GenericProductDto> LimitedResults(Long limit) {
//        return null;
//    }

    @Override
    public GenericProductDto DeleteProduct(Long Id) {
        return null;
    }

    @Override
    public GenericProductDto UpdateProduct(Long Id) {
        return null;
    }
}
