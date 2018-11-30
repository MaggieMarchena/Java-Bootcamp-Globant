package org.springframework.ShoppingCart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	private Map<Long, Cart> carts = new HashMap<>();
	private AtomicLong counter = new AtomicLong();
	
	public Cart addCart(Cart cart) {
		if (cart.getId() == null) {
			cart.setId(counter.incrementAndGet());
		}
		this.carts.put(cart.getId(), cart);
		return cart;
	}
	
	public Cart getCart(Long cartID) {
		if (this.carts.containsKey(cartID)){
			return this.carts.get(cartID);
		}
		return null;
	}
	
	public List<Cart> getAllCarts(){
		return (List<Cart>) this.carts.values();
	}
	
	public Cart deleteCart(Long cartID) {
		Cart delete = this.getCart(cartID);
		if(delete != null) {
			this.carts.remove(cartID);
			return delete;
		}
		return null;
	}
	
	public Cart addProduct(Long cartID, Product product) {
		Cart cart = this.getCart(cartID);
		if (cart != null) {
			cart.addProduct(product);
			return cart;
		}
		return null;
	}
	
	public Cart removeProduct(Long cartID, Product product) {
		Cart cart = this.getCart(cartID);
		if (cart != null) {
			cart.removeProduct(product);
			return cart;
		}
		return null;
	}
	
}