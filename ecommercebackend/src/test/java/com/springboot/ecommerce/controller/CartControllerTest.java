package com.michaelakamihe.ecommercebackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.michaelakamihe.ecommercebackend.model.Product;
import com.michaelakamihe.ecommercebackend.model.User;
import com.michaelakamihe.ecommercebackend.model.cart.CartItem;
import com.michaelakamihe.ecommercebackend.service.CartItemService;
import com.michaelakamihe.ecommercebackend.service.ProductService;
import com.michaelakamihe.ecommercebackend.service.UserService;

public class CartControllerTest {

    @InjectMocks
    CartController cartController;

    @Mock
    UserService userService;

    @Mock
    ProductService productService;

    @Mock
    CartItemService cartItemService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCartItems() {
        CartItem cartItem1 = new CartItem();
        CartItem cartItem2 = new CartItem();
        List<CartItem> cartItemList = Arrays.asList(cartItem1, cartItem2);

        when(cartItemService.getCartItems()).thenReturn(cartItemList);

         ResponseEntity<List<CartItem>> result = cartController.getCartItems();

        assertEquals(result.getBody().size(), 2);
        verify(cartItemService, times(1)).getCartItems();
    }

    @Test
    public void testGetCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.getPk().getUser().setId(1L);
        cartItem.getPk().getProduct().setId(1L);

        when(cartItemService.getCartItem(1L, 1L)).thenReturn(cartItem);

        ResponseEntity<CartItem> result = cartController.getCartItem(1L, 1L);

        assertEquals(result.getBody().getPk().getUser().getId(), cartItem.getPk().getUser().getId());
        assertEquals(result.getBody().getPk().getProduct().getId(), cartItem.getPk().getProduct().getId());
        verify(cartItemService, times(1)).getCartItem(1L, 1L);
    }

    @Test
    public void testGetUserCart() {
        User user = new User();
        user.setId(1L);

        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<List<CartItem>> result = cartController.getUserCart(1L);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(userService, times(1)).getUser(1L);
    }

    @Test
    public void testAddToUserCart() {
        User user = new User();
        user.setId(1L);
        Product product = new Product();
        product.setId(1L);

        CartItem cartItem = new CartItem(user, product, 1);

        when(userService.getUser(1L)).thenReturn(user);
        when(productService.getProduct(1L)).thenReturn(product);
        when(cartItemService.addCartItem(cartItem)).thenReturn(cartItem);

        ResponseEntity<User> result = cartController.addToUserCart(1L, 1L);

        assertEquals(result.getBody().getId(), user.getId());
        verify(userService, times(1)).getUser(1L);
        verify(productService, times(1)).getProduct(1L);
        verify(cartItemService, times(1)).addCartItem(cartItem);
    }

    @Test
    public void testUpdateCartItem() {
        User user = new User();
        user.setId(1L);
        Product product = new Product();
        product.setId(1L);

        CartItem cartItem = new CartItem(user, product, 1);

        when(userService.getUser(1L)).thenReturn(user);
        when(productService.getProduct(1L)).thenReturn(product);
        when(cartItemService.updateCartItem(cartItem)).thenReturn(cartItem);

        ResponseEntity<User> result = cartController.updateCartItem(1L, 1L, cartItem);

        assertEquals(result.getBody().getId(), user.getId());
        verify(userService, times(1)).getUser(1L);
        verify(productService, times(1)).getProduct(1L);
        verify(cartItemService, times(1)).updateCartItem(cartItem);
    }

    @Test
    public void testRemoveFromUserCart() {
        doNothing().when(cartItemService).deleteCartItem(1L, 1L);

        User user = new User();
        user.setId(1L);

        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<User> result = cartController.removeFromUserCart(1L, 1L);

        assertEquals(result.getBody().getId(), user.getId());
        verify(cartItemService, times(1)).deleteCartItem(1L, 1L);
        verify(userService, times(1)).getUser(1L);
    }
}
