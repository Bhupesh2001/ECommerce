package com.michaelakamihe.ecommercebackend.service;

import com.michaelakamihe.ecommercebackend.model.Product;
import com.michaelakamihe.ecommercebackend.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getProducts();

        assertEquals(result.size(), 2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertEquals(result.getId(), product.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test");

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.addProduct(product);

        assertEquals(result.getName(), "Test");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated");
        Product result = productService.updateProduct(1L, updatedProduct);

        assertEquals(result.getName(), "Updated");
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
