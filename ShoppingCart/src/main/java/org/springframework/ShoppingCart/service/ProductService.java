package org.springframework.ShoppingCart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product add(Product product) {
		this.productRepository.save(product);
		return product;
	}
	
	public Product get(Long productID) throws IllegalArgumentException{
		Optional<Product> product = this.productRepository.findById(productID);
		if(product.equals(Optional.empty())) {
			throw new IllegalArgumentException("Product not found with Id: " + productID);
		}
		return product.get();
	}
	
	public List<Product> getAll(){
		return (List<Product>) this.productRepository.findAll();
	}
	
	public Product setName(Long productID, String name) throws IllegalArgumentException{
		Product product = this.get(productID);
		product.setName(name);
		return this.productRepository.save(product);
	}
	
	public Product setPrice(Long productID, Double price) throws IllegalArgumentException{
		Product product = this.get(productID);
		product.setPrice(price);
		return this.productRepository.save(product);
	}
	
	public Product delete(Long productID) throws IllegalArgumentException{
		Product delete = this.get(productID);
		this.productRepository.delete(delete);
		return delete;
	}

}