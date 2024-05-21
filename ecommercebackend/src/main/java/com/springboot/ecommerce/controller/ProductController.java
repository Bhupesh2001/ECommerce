package com.michaelakamihe.ecommercebackend.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelakamihe.ecommercebackend.model.Product;
import com.michaelakamihe.ecommercebackend.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService productService;


	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		System.out.println(product);
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
