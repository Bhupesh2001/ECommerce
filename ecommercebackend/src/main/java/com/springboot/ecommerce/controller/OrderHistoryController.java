package com.michaelakamihe.ecommercebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelakamihe.ecommercebackend.model.OrderHistory;
import com.michaelakamihe.ecommercebackend.service.OrderHistoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderHistoryController {
	
	@Autowired 
	OrderHistoryService orderHistoryService;


	@PostMapping("/order-history")
	public ResponseEntity<OrderHistory> addOrderToHistory(@RequestBody OrderHistory orderHistory) {
		return ResponseEntity.ok(orderHistoryService.addOrderToHistory(orderHistory));
	}

	@GetMapping("/order-history/{userId}")
	public ResponseEntity<List<OrderHistory>> getAllOrders(@PathVariable("userId") Long userId) {
		List<OrderHistory> orderHistory = orderHistoryService.getOrderHistory((long)userId);
		System.out.println(orderHistory);
		return ResponseEntity.ok(orderHistory);
	}
}
