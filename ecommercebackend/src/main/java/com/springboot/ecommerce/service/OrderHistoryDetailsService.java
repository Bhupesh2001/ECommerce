package com.michaelakamihe.ecommercebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michaelakamihe.ecommercebackend.model.OrderHistoryDetails;
import com.michaelakamihe.ecommercebackend.repo.OrderHistoryDetailsRepository;

@Service
public class OrderHistoryDetailsService {
	
	private final OrderHistoryDetailsRepository repo;

	public OrderHistoryDetailsService(OrderHistoryDetailsRepository repo) {
		this.repo = repo;
	}
	
	public List<OrderHistoryDetails> addOrderHistoryDetails(List<OrderHistoryDetails> details) {
		return repo.saveAll(details);
	}
	
	public List<OrderHistoryDetails> getOrderHistoryDetails(long orderId){
		return repo.findByOrderId(orderId);
	}
	
}
