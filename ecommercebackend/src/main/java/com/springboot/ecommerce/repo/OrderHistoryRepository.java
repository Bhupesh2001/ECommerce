package com.michaelakamihe.ecommercebackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaelakamihe.ecommercebackend.model.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long>{
	List<OrderHistory> findByUserId(long userId);
}
