package com.michaelakamihe.ecommercebackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "order_history")
@Data
public class OrderHistory {
	public OrderHistory() {}
	
	public OrderHistory(long userId, double totalAmount) {
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderDate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@Column
	private long userId;
	

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderDate = new Date();
	
	@Column
	private double totalAmount;
	
}
