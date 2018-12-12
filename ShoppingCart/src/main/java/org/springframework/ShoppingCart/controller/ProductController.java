package org.springframework.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ShoppingCart.dto.ProductDto;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
		try {
			return this.convertToDto(this.productService.get(productID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found", ex);
		}
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
		try {
			return this.convertToDto(this.productService.setName(id, name));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Product Id", ex);
		}
	}
	
	@PutMapping("/products/{id}/attributes/{price}")
	public ProductDto setPrice(@PathVariable("id") Long id, @PathVariable("price") Double price) {
		try {
			return this.convertToDto(this.productService.setPrice(id, price));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Product Id", ex);
		}
	}

	@DeleteMapping("/products/{id}")
	public ProductDto removeProduct(@PathVariable("id") long productID) {
		try {
			return this.convertToDto(this.productService.delete(productID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found", ex);
		}
	}

	//Conversion
	private ProductDto convertToDto(Product product) {
		return  modelMapper.map(product, ProductDto.class);    
	}
		
	private Product convertToEntity(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);         
	}
}