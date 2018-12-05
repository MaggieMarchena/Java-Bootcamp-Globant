package org.springframework.ShoppingCart.service;

import java.util.List;

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
	
	public Product get(Long productID) {
		return this.productRepository.findById(productID).get();
	}
	
	public List<Product> getAll(){
		return (List<Product>) this.productRepository.findAll();
	}
	
	public Product setName(Long productID, String name) {
		Product product = this.get(productID);
		product.setName(name);
		return this.productRepository.save(product);
	}
	
	public Product setPrice(Long productID, Double price) {
		Product product = this.get(productID);
		product.setPrice(price);
		return this.productRepository.save(product);
	}
	
	public Product delete(Long productID) {
		Product delete = this.get(productID);
		this.productRepository.delete(delete);
		return delete;
	}

}