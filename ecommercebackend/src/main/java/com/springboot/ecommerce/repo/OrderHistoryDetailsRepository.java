package com.michaelakamihe.ecommercebackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaelakamihe.ecommercebackend.model.OrderHistoryDetails;

@Repository
public interface OrderHistoryDetailsRepository extends JpaRepository<OrderHistoryDetails,Long>{
	List<OrderHistoryDetails> findByOrderId(long orderId);
}
