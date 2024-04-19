package com.example.productservice.thirpartyclients;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String allProductsUrl = "https://fakestoreapi.com/products/";
    private  String createProductUrl ="https://fakestoreapi.com/products";
    private  String limitedProductsUrl = "https://fakestoreapi.com/products?limit={limit}";
    private String updateProductUrl ="https://fakestoreapi.com/carts/7";
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDto.class,
                id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " not found");
        }


        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        // Create a ParameterizedTypeReference for a List of FakeStoreProductDto
        ParameterizedTypeReference<List<FakeStoreProductDto>> responseType =
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {};

        // Make the HTTP GET request and retrieve the response entity using ParameterizedTypeReference
        ResponseEntity<List<FakeStoreProductDto>> responseEntity =
                restTemplate.exchange(allProductsUrl,
                        HttpMethod.GET,
                        null,
                        responseType);

        // Retrieve the list of products
       return responseEntity.getBody();

       // return fakeStoreProductDtos;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(
                createProductUrl,
                product,
                FakeStoreProductDto.class
        );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> LimitedResults(Long limit) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response=
                restTemplate.getForEntity(
                        limitedProductsUrl,
                        FakeStoreProductDto[].class,limit);

        List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.asList(response.getBody());
        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto DeleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(
                        productUrl,
                        HttpMethod.DELETE,
                        null,
                        FakeStoreProductDto.class,id);
        //restTemplate.execute()//has greater control over it
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
         return  fakeStoreProductDto;
    }

    public FakeStoreProductDto UpdateProduct(Long id) {
        RestTemplate restTemplate =restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.exchange(
                productUrl,
                HttpMethod.PUT,
                null,
                FakeStoreProductDto.class,
                id
        );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return  fakeStoreProductDto;
    }

}
