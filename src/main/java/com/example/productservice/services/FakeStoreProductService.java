package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String allProductsUrl = "https://fakestoreapi.com/products/";
    private  String createProductUrl ="https://fakestoreapi.com/products";
    private  String limitedProductsUrl = "https://fakestoreapi.com/products?limit={limit}";
    private String updateProductUrl ="https://fakestoreapi.com/carts/7";
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();

      ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDto.class,
                id);
      FakeStoreProductDto fakeStoreProductDto = response.getBody();

      GenericProductDto genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);

       return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
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
        List<FakeStoreProductDto> fakeStoreProductDtos = responseEntity.getBody();

//        ResponseEntity<FakeStoreProductDto[]> response=
//             restTemplate.getForEntity(
//                     allProductsUrl,
//                     FakeStoreProductDto[].class);
//
//        List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.asList(response.getBody());
//
        GenericProductDto genericProductDto;
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response =  restTemplate.postForEntity(
                createProductUrl,
                product,
                FakeStoreProductDto.class
                );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> LimitedResults(Long limit) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response=
                restTemplate.getForEntity(
                        limitedProductsUrl,
                        FakeStoreProductDto[].class,limit);

        List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.asList(response.getBody());
        // Limit the results on the client side (e.g., to 10 records)
        //List<FakeStoreProductDto> limitedList = productList.subList(0, Math.min(productList.size(), 10));
        GenericProductDto genericProductDto;
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public GenericProductDto DeleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
       ResponseEntity<FakeStoreProductDto> response =
               restTemplate.exchange(
                       productUrl,
                       HttpMethod.DELETE,
                       null,
                       FakeStoreProductDto.class,id);
        //restTemplate.execute()//has greater control over it
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        return  genericProductDto;
    }

    @Override
    public GenericProductDto UpdateProduct(Long id) {
        RestTemplate restTemplate =restTemplateBuilder.build();
      ResponseEntity<FakeStoreProductDto> response =  restTemplate.exchange(
                productUrl,
                HttpMethod.PUT,
                null,
                FakeStoreProductDto.class,
                id
                );
      FakeStoreProductDto fakeStoreProductDto = response.getBody();
      GenericProductDto genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
      return  genericProductDto;
    }
    public GenericProductDto ConvertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());

        return genericProductDto;
    }
}
