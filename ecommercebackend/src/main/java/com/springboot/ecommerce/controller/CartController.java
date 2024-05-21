package com.michaelakamihe.ecommercebackend.controller;

import java.util.List;

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
import com.michaelakamihe.ecommercebackend.model.User;
import com.michaelakamihe.ecommercebackend.model.cart.CartItem;
import com.michaelakamihe.ecommercebackend.model.cart.CartItemPK;
import com.michaelakamihe.ecommercebackend.service.CartItemService;
import com.michaelakamihe.ecommercebackend.service.ProductService;
import com.michaelakamihe.ecommercebackend.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CartController {
	
	@Autowired 
	UserService userService;

	@Autowired 
	ProductService productService;

	@Autowired 
	CartItemService cartItemService;


	@GetMapping("/cart-items")
	public ResponseEntity<List<CartItem>> getCartItems() {
		return ResponseEntity.ok(cartItemService.getCartItems());
	}

	@CrossOrigin
	@GetMapping("/cart-items/{id}/{productId}")
	public ResponseEntity<CartItem> getCartItem(@PathVariable("id") Long id,
			@PathVariable("productId") Long productId) {
		return ResponseEntity.ok(cartItemService.getCartItem(id, productId));
	}
	
	@GetMapping("/users/{id}/cart")
	public ResponseEntity<List<CartItem>> getUserCart(@PathVariable("id") Long id) {
		System.out.println(userService.getUser(id).getCartItems().size());
		return new ResponseEntity<>(userService.getUser(id).getCartItems(), HttpStatus.OK);
	}

	@PostMapping("/users/{id}/cart/add/{productId}")
	public ResponseEntity<User> addToUserCart(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
		User user = userService.getUser(id);
		Product product = productService.getProduct(productId);

		CartItem cartItem = new CartItem(user, product, 1);
		cartItemService.addCartItem(cartItem);

		return new ResponseEntity<>(userService.getUser(id), HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}/cart/update/{productId}")
	public ResponseEntity<User> updateCartItem(@PathVariable("id") Long id, @PathVariable("productId") Long productId,
			@RequestBody CartItem cartItem) {
		User user = userService.getUser(id);
		Product product = productService.getProduct(productId);

		cartItem.setPk(new CartItemPK(user, product));
		cartItemService.updateCartItem(cartItem);

		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}/cart/remove/{productId}")
	public ResponseEntity<User> removeFromUserCart(@PathVariable("id") Long id,
			@PathVariable("productId") Long productId) {
		cartItemService.deleteCartItem(id, productId);

		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

}
