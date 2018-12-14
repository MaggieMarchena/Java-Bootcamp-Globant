package org.springframework.ShoppingCart.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public Product add(Product product) throws IllegalArgumentException{
		if(product == null) {
			throw new IllegalArgumentException("Product can't be null");
		}
		rwLock.writeLock().lock();
		try {
			return this.productRepository.save(product);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public Product get(Long productID) throws IllegalArgumentException{
		rwLock.readLock().lock();
		try {
			Optional<Product> product = this.productRepository.findById(productID);
			if(product.equals(Optional.empty())) {
				throw new IllegalArgumentException("Product not found with Id: " + productID);
			}
			return product.get();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public List<Product> getAll(){
		rwLock.readLock().lock();
		try {
			return (List<Product>) this.productRepository.findAll();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public Product setName(Long productID, String name) throws IllegalArgumentException{
		Product product = this.get(productID);
		rwLock.writeLock().lock();
		try {
			product.setName(name);
			return this.productRepository.save(product);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public Product setPrice(Long productID, Double price) throws IllegalArgumentException{
		Product product = this.get(productID);
		rwLock.writeLock().lock();
		try {
			product.setPrice(price);
			return this.productRepository.save(product);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public Product delete(Long productID) throws IllegalArgumentException{
		Product delete = this.get(productID);
		rwLock.writeLock().lock();
		try {
			this.productRepository.delete(delete);
			return delete;
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}

}