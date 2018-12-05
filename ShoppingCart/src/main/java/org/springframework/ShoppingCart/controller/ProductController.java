package org.springframework.ShoppingCart.controller;

import java.util.List;

import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.service.ProductService;
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
public class ProductController{
	
	private ProductService productService;
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		return this.productService.add(product);
	}

	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable("id") Long productID) {
		return this.productService.get(productID);
	}
	
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return this.productService.getAll();
	}
	
	@PutMapping("/product//{id}/attributes/{name}")
	public Product setName(@PathVariable("id") Long id, @PathVariable("name") String name) {
		return this.productService.setName(id, name);
	}
	
	@PutMapping("/product//{id}/attributes/{price}")
	public Product setPrice(@PathVariable("id") Long id, @PathVariable("price") Double price) {
		return this.productService.setPrice(id, price);
	}

	@DeleteMapping("/product/{id}")
	public Product removeProduct(@PathVariable("id") long productID) {
		return this.productService.delete(productID);
	}

}