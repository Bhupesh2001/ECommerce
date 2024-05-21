package com.michaelakamihe.ecommercebackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_history_details")
@Data
@NoArgsConstructor
public class OrderHistoryDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
		
	@Column
	private long orderId;
	@Column
	private int quantity;
	@Column
	private double price;
	@Column
	private String productName;
	
	
	public OrderHistoryDetails(int orderId, int quantity, double price, String productName) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.price = price;
		this.productName = productName;
	}
}
