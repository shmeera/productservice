package com.example.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product extends BaseModel{
    private String title;
    private String name;
    private String description;
    private String image;
    private double price;
    private String category;
}
