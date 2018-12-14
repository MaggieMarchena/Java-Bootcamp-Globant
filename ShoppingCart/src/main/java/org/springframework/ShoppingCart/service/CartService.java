package org.springframework.ShoppingCart.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public Cart addCart(Cart cart) throws IllegalArgumentException{
		if(cart == null) {
			throw new IllegalArgumentException("Cart can't be null");
		}
		rwLock.writeLock().lock();
		try {
			return this.cartRepository.save(cart);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public Cart getCart(Long cartID) throws IllegalArgumentException{
		rwLock.readLock().lock();
		try {
			Optional<Cart> cart = this.cartRepository.findById(cartID);
			if(cart.equals(Optional.empty())) {
				throw new IllegalArgumentException("Cart not found with Id: " + cartID);
			}
			return cart.get();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public List<Cart> getAllCarts(){
		rwLock.readLock().lock();
		try {
			return (List<Cart>) this.cartRepository.findAll();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public Cart deleteCart(Long cartID) throws IllegalArgumentException{
		Cart delete = this.getCart(cartID);
		rwLock.writeLock().lock();
		try {
			this.cartRepository.delete(delete);
			return delete;
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public User getUser(Long cartID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		rwLock.readLock().lock();
		try {
			return this.userRepository.findById(cart.getUser().getId()).get();
		}
		finally {
			rwLock.readLock();
		}
	}
	
	public Cart setUser(Long cartID, Long userID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		rwLock.readLock().lock();
		rwLock.writeLock().lock();
		try {
			User user = this.userRepository.findById(userID).get();
			cart.setUser(user);
			return this.cartRepository.save(cart);
		}
		finally {
			rwLock.readLock().unlock();
			rwLock.writeLock().unlock();
		}
	}
	
	public List<Product> getAllProducts(Long cartID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		rwLock.readLock().lock();
		try {
			return cart.getProducts();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public Cart addProduct(Long cartID, Long productID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		rwLock.readLock().lock();
		rwLock.writeLock().lock();
		try {
			Product product = this.productRepository.findById(productID).get();
			cart.addProduct(product);
			this.cartRepository.save(cart);
			return cart;
		}
		finally {
			rwLock.readLock().unlock();
			rwLock.writeLock().unlock();
		}
	}
	
	public Cart removeProduct(Long cartID, Long productID) throws IllegalArgumentException{
		Cart cart = this.getCart(cartID);
		rwLock.readLock().lock();
		rwLock.writeLock().lock();
		try {
			Product product = this.productRepository.findById(productID).get();
			cart.removeProduct(product);
			return this.cartRepository.save(cart);
		}
		finally {
			rwLock.readLock().unlock();
			rwLock.writeLock().unlock();
		}
	}
	
}