package com.example.productservice.controllers;

import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@Nullable  @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id)  throws NotFoundException{
        return productService.getProductById(id);
    }
//    @PostMapping("/validate")
//    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto request) {
//        SessionStatus sessionStatus = authService.validate(request.getUserId(),request.getToken());
//
//        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
//    }
//    @GetMapping()
//    public List<GenericProductDto> getAllProducts(){
//        return productService.getAllProducts();
//    }
////    @GetMapping("limit/{limit}")
////    public  List<GenericProductDto> getLimitedResults(@PathVariable Long limit){
////        return productService.LimitedResults(limit);
////    }
//    public void updateProductId(Long id){
//
//    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto)
    {
        return  productService.createProduct(genericProductDto);
    }
    @DeleteMapping("{id}")
    public  GenericProductDto DeleteProductId(@PathVariable Long id){
        return  productService.DeleteProduct(id);
    }
    @PutMapping("{id}")
    public  GenericProductDto UpdateProductId(@PathVariable Long id){
        return  productService.UpdateProduct(id);
    }
    //specific to this controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return  new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,
//                notFoundException.getMessage()),HttpStatus.NOT_FOUND);
//
//    }
}
