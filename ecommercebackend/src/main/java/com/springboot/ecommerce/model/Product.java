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

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 4000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private double price;

    @Column(nullable = false)
    private String url;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date addedOn = new Date();


    public Product () {
    }

    public Product (String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product (String name, String description, double price,String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
