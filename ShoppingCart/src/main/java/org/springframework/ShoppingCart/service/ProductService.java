package org.springframework.ShoppingCart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService{
	
	private Map<Long, Product> products = new HashMap<>();
	private AtomicLong counter = new AtomicLong();
	
	public Product add(Product product) {
		if (product.getId() == null) {
			product.setId(counter.incrementAndGet());
		}
		this.products.put(product.getId(), product);
		return product;
	}
	
	public Product get(Long productID) {
		if (this.products.containsKey(productID)){
			return this.products.get(productID);
		}
		return null;
	}
	
	public List<Product> getAll(){
		return (List<Product>) this.products.values();
	}
	
	public Product delete(Long productID) {
		Product delete = this.get(productID);
		if(delete != null) {
			this.products.remove(productID);
			return delete;
		}
		return null;
	}

}