package org.springframework.ShoppingCart.dto;

import java.util.List;

import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.model.User;

public class CartDto{
	
	private Long id;
	private User user;
	private List<Product> products;
	
	public CartDto() {
	}

	public CartDto(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}