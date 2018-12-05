package org.springframework.ShoppingCart.controller;

import java.util.List;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.service.CartService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ShoppingCart/v1")
public class CartController{
	
	private CartService cartService;
	
	//Cart CRUD
	@PostMapping("/cart")
	public Cart addCart(@RequestBody Cart cart) {
		return this.cartService.addCart(cart);
	}
	
	@GetMapping("/cart/{id}")
	public Cart getCart(@PathVariable("id") Long cartID) {
		return this.cartService.getCart(cartID);
	}
	
	@GetMapping("/cart")
	public List<Cart> getAllCarts() {
		return this.cartService.getAllCarts();
	}
	
	@DeleteMapping("/cart/{id}")
	public Cart removeCart(@PathVariable("id") Long cartID) {
		return this.cartService.deleteCart(cartID);
	}
	
	//User
	@PutMapping("/cart/{cartID}/user/{userID}")
	public Cart setUser(@PathVariable("cartID") Long cartID, @PathVariable("userID") Long userID) {
		return this.cartService.setUser(cartID, userID);
	}
	
	@GetMapping("/cart/{cartID}/user")
	public User getUser(@PathVariable("cartID") Long cartID) {
		return this.cartService.getUser(cartID);
	}
	
	//Products in cart
	@PutMapping("/cart/{cartID}/products/{productID}")
	public Cart addProduct(@PathVariable("cartID") Long cartID, @PathVariable("productID") Long productID) {
		return this.cartService.addProduct(cartID, productID);
	}

	@DeleteMapping("/cart/{cartID}/products/{productID}")
	public Cart removeProduct(@PathVariable("cartID") Long cartID, @PathVariable("productID") Long productID) {
		return this.cartService.removeProduct(cartID, productID);
	}
}