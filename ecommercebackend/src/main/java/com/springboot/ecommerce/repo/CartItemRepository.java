package com.michaelakamihe.ecommercebackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michaelakamihe.ecommercebackend.model.cart.CartItem;
import com.michaelakamihe.ecommercebackend.model.cart.CartItemPK;

public interface CartItemRepository extends JpaRepository <CartItem, CartItemPK> {
}
