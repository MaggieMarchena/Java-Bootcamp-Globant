package org.springframework.ShoppingCart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.repository.CartRepository;
import org.springframework.ShoppingCart.repository.ProductRepository;
import org.springframework.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public Cart addCart(Cart cart) {
		return this.cartRepository.save(cart);
	}
	
	public Cart getCart(Long cartID) throws IllegalArgumentException{
		Optional<Cart> cart = this.cartRepository.findById(cartID);
		if(cart.equals(Optional.empty())) {
			throw new IllegalArgumentException("Cart not found with Id: " + cartID);
		}
		return cart.get();
	}
	
	public List<Cart> getAllCarts(){
		return (List<Cart>) this.cartRepository.findAll();
	}
	
	public Cart deleteCart(Long cartID) throws IllegalArgumentException{
		Cart delete = this.getCart(cartID);
		this.cartRepository.delete(delete);
		return delete;
	}
	
	public User getUser(Long cartID) throws IllegalArgumentException{
		User user = this.getCart(cartID).getUser();
		return user;
	}
	
	public Cart setUser(Long cartID, Long userID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		User user = this.userRepository.findById(userID).get();
		cart.setUser(user);
		this.cartRepository.save(cart);
		return cart;
	}
	
	public List<Product> getAllProducts(Long cartID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		List<Product> products = cart.getProducts();
		return products;
	}
	
	public Cart addProduct(Long cartID, Long productID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		Product product = this.productRepository.findById(productID).get();
		cart.addProduct(product);
		this.cartRepository.save(cart);
		return cart;
	}
	
	public Cart removeProduct(Long cartID, Long productID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		Product product = this.productRepository.findById(productID).get();
		cart.removeProduct(product);
		this.cartRepository.save(cart);
		return cart;
	}
	
}