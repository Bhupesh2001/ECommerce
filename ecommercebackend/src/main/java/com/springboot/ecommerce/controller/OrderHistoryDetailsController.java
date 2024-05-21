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

import com.michaelakamihe.ecommercebackend.model.OrderHistoryDetails;
import com.michaelakamihe.ecommercebackend.service.OrderHistoryDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderHistoryDetailsController {

	@Autowired
	OrderHistoryDetailsService orderHistoryDetailsService;
	

	@GetMapping("/order-history-detail/{orderId}")
	public ResponseEntity<List<OrderHistoryDetails>> getOrderHistoryDetails(@PathVariable("orderId") Long orderId) {
		return ResponseEntity.ok(orderHistoryDetailsService.getOrderHistoryDetails(orderId));

	}
	
	@PostMapping("/order-history-detail")
	public ResponseEntity<List<OrderHistoryDetails>> addOrderHistoryDetails(@RequestBody List<OrderHistoryDetails> details) {
		return ResponseEntity.ok(orderHistoryDetailsService.addOrderHistoryDetails(details));
	}
}
