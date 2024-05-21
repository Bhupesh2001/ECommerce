package com.michaelakamihe.ecommercebackend;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.michaelakamihe.ecommercebackend.model.OrderHistory;
import com.michaelakamihe.ecommercebackend.service.OrderHistoryService;

@SpringBootTest
class EcommercebackendApplicationTests {

	@Autowired
	OrderHistoryService service;
	
	@Test
	void contextLoads() {
		List<OrderHistory> orderHistories = service.getOrderHistory(3);
		System.out.println(orderHistories);
	}

}
