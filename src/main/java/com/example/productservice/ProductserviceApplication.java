package com.example.productservice;

import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	private PriceRepository priceRepository;

	public ProductserviceApplication(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Category category = new Category();
//		category.setName("electronics");
//
//		Category savedCategory = categoryRepository.save(category);
//
//		Price price = new Price("Rupee", 10.0);
//		// Price savedPrice = priceRepository.save(price);
//
//		Product product = new Product();
//		product.setTitle("iPhone4");
//		product.setImage("image url");
//		product.setDescription("Best phone ever");
//		product.setCategory(savedCategory);
//		product.setPrice(price);
//
//		productRepository.save(product);
//
//		Product product1 = productRepository.findByTitle("iPhone4");
//		System.out.println(product1);
	}
}
