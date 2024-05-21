package com.michaelakamihe.ecommercebackend.controller;

import com.michaelakamihe.ecommercebackend.model.Product;
import com.michaelakamihe.ecommercebackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getProducts()).thenReturn(productList);

        ResponseEntity<List<Product>> result = productController.getProducts();

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().size(), 2);
        verify(productService, times(1)).getProducts();
    }

    @Test
    public void testGetProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productService.getProduct(1L)).thenReturn(product);

        ResponseEntity<Product> result = productController.getProduct(1L);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getId(), product.getId());
        verify(productService, times(1)).getProduct(1L);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productService.addProduct(product)).thenReturn(product);

        ResponseEntity<Product> result = productController.addProduct(product);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getId(), product.getId());
        verify(productService, times(1)).addProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productService.updateProduct(1L, product)).thenReturn(product);

        ResponseEntity<Product> result = productController.updateProduct(1L, product);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getId(), product.getId());
        verify(productService, times(1)).updateProduct(1L, product);
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<?> result = productController.deleteProduct(1L);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(productService, times(1)).deleteProduct(1L);
    }
}
