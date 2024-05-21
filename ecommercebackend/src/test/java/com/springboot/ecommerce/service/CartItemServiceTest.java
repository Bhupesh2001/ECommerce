package com.michaelakamihe.ecommercebackend.service;

import com.michaelakamihe.ecommercebackend.model.cart.CartItem;
import com.michaelakamihe.ecommercebackend.repo.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartItemServiceTest {

    @InjectMocks
    CartItemService cartItemService;

    @Mock
    CartItemRepository cartItemRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCartItems() {
        CartItem cartItem1 = new CartItem();
        CartItem cartItem2 = new CartItem();
        List<CartItem> cartItemList = Arrays.asList(cartItem1, cartItem2);

        when(cartItemRepository.findAll()).thenReturn(cartItemList);

        List<CartItem> result = cartItemService.getCartItems();

        assertEquals(result.size(), 2);
        verify(cartItemRepository, times(1)).findAll();
    }

    @Test
    public void testGetCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.getPk().getUser().setId(1L);
        cartItem.getPk().getProduct().setId(1L);

        when(cartItemRepository.findAll()).thenReturn(Arrays.asList(cartItem));

        CartItem result = cartItemService.getCartItem(1L, 1L);

        assertEquals(result.getPk().getUser().getId(), cartItem.getPk().getUser().getId());
        assertEquals(result.getPk().getProduct().getId(), cartItem.getPk().getProduct().getId());
    }

    @Test
    public void testAddCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.getPk().getUser().setId(1L);
        cartItem.getPk().getProduct().setId(1L);

        when(cartItemRepository.findAll()).thenReturn(Arrays.asList());
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItem result = cartItemService.addCartItem(cartItem);

        assertEquals(result.getPk().getUser().getId(), cartItem.getPk().getUser().getId());
        assertEquals(result.getPk().getProduct().getId(), cartItem.getPk().getProduct().getId());
        verify(cartItemRepository, times(1)).save(cartItem);
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.getPk().getUser().setId(1L);
        cartItem.getPk().getProduct().setId(1L);
        cartItem.setQuantity(1);

        when(cartItemRepository.findAll()).thenReturn(Arrays.asList(cartItem));
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItem updatedCartItem = new CartItem();
        updatedCartItem.getPk().getUser().setId(1L);
        updatedCartItem.getPk().getProduct().setId(1L);
        updatedCartItem.setQuantity(2);
        CartItem result = cartItemService.updateCartItem(updatedCartItem);

        assertEquals(result.getQuantity(), 2);
        verify(cartItemRepository, times(1)).save(cartItem);
    }

    @Test
    public void testDeleteCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.getPk().getUser().setId(1L);
        cartItem.getPk().getProduct().setId(1L);

        when(cartItemRepository.findAll()).thenReturn(Arrays.asList(cartItem));

        cartItemService.deleteCartItem(1L, 1L);

        verify(cartItemRepository, times(1)).delete(cartItem);
    }
}
