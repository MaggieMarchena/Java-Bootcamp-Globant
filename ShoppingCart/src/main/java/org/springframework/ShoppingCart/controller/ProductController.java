package org.springframework.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ShoppingCart.dto.ProductDto;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ProductService productService;
	@Autowired
    private ModelMapper modelMapper;
	
	@PostMapping("/products")
	public ProductDto addProduct(@RequestBody ProductDto product) {
		return this.convertToDto(this.productService.add(this.convertToEntity(product)));
	}

	@GetMapping("/products/{id}")
	public ProductDto getProduct(@PathVariable("id") Long productID) {
		return this.convertToDto(this.productService.get(productID));
	}
	
	@GetMapping("/products")
	public List<ProductDto> getAllProducts() {
		List<Product> products = this.productService.getAll();
		List<ProductDto> result = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			result.add(this.convertToDto(products.get(i)));
		}
		return result;
	}
	
	@PutMapping("/products/{id}/attributes/{name}")
	public ProductDto setName(@PathVariable("id") Long id, @PathVariable("name") String name) {
		return this.convertToDto(this.productService.setName(id, name));
	}
	
	@PutMapping("/products/{id}/attributes/{price}")
	public ProductDto setPrice(@PathVariable("id") Long id, @PathVariable("price") Double price) {
		return this.convertToDto(this.productService.setPrice(id, price));
	}

	@DeleteMapping("/products/{id}")
	public ProductDto removeProduct(@PathVariable("id") long productID) {
		return this.convertToDto(this.productService.delete(productID));
	}

	//Conversion
	private ProductDto convertToDto(Product product) {
		return  modelMapper.map(product, ProductDto.class);    
	}
		
	private Product convertToEntity(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);         
	}
}