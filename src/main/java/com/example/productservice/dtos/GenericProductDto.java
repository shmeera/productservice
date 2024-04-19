package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto  implements  java.io.Serializable{
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
