package com.michaelakamihe.ecommercebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michaelakamihe.ecommercebackend.model.OrderHistory;
import com.michaelakamihe.ecommercebackend.repo.OrderHistoryRepository;

@Service
public class OrderHistoryService {
	
	private final OrderHistoryRepository repo;
	
	public OrderHistoryService(OrderHistoryRepository repo) {
		this.repo = repo;
	}

	public OrderHistory addOrderToHistory(OrderHistory orderDetails) {
		return repo.save(orderDetails);
	}
	
	public List<OrderHistory> getOrderHistory(long userId) {
		return repo.findByUserId(userId);
	}
}
