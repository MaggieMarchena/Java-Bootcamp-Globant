package org.springframework.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ShoppingCart.dto.CartDto;
import org.springframework.ShoppingCart.dto.ProductDto;
import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.service.CartService;
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
public class CartController{
	
	@Autowired
	private CartService cartService;
	@Autowired
    private ModelMapper modelMapper;
	
	//Cart CRUD
	@PostMapping("/carts")
	public CartDto addCart(@RequestBody CartDto cart) {
		return this.convertToDto(this.cartService.addCart(this.convertToEntity(cart)));
	}
	
	@GetMapping("/carts/{id}")
	public CartDto getCart(@PathVariable("id") Long cartID) {
		try {
			return this.convertToDto(this.cartService.getCart(cartID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found", ex);
		}
	}
	
	@GetMapping("/carts")
	public List<CartDto> getAllCarts() {
		List<Cart> carts = this.cartService.getAllCarts();
		List<CartDto> result = new ArrayList<>();
		for(int i = 0; i < carts.size(); i++) {
        	result.add(this.convertToDto(carts.get(i)));
        }
        return result;   
	}
	
	@DeleteMapping("/carts/{id}")
	public CartDto removeCart(@PathVariable("id") Long cartID) {
		try {
			return this.convertToDto(this.cartService.deleteCart(cartID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found", ex);
		}
	}
	
	//User
	@PutMapping("/carts/{cartID}/users/{userID}")
	public CartDto setUser(@PathVariable("cartID") Long cartID, @PathVariable("userID") Long userID) {
		try {
			return this.convertToDto(this.cartService.setUser(cartID, userID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Cart Id", ex);
		}
	}
	
	//Products in cart
	@GetMapping("/carts/{cartID}/products")
	public List<ProductDto> detAllProducts(@PathVariable("cartID") Long cartID) {
		try {
			List<Product> products = this.cartService.getAllProducts(cartID);
			List<ProductDto> result = new ArrayList<>();
			for(int i=0; i<products.size(); i++) {
				result.add(this.convertToDto(products.get(i)));
			}
			return result;
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Cart Id", ex);
		}
	}
	
	@PutMapping("/carts/{cartID}/products/{productID}")
	public CartDto addProduct(@PathVariable("cartID") Long cartID, @PathVariable("productID") Long productID) {
		try {
			return this.convertToDto(this.cartService.addProduct(cartID, productID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Cart Id", ex);
		}
	}

	@DeleteMapping("/carts/{cartID}/products/{productID}")
	public CartDto removeProduct(@PathVariable("cartID") Long cartID, @PathVariable("productID") Long productID) {
		try {
			return this.convertToDto(this.cartService.removeProduct(cartID, productID));
		}
		catch(IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Cart Id", ex);
		}
	}
	
	//Conversion
	private CartDto convertToDto(Cart cart) {
        return  modelMapper.map(cart, CartDto.class);    
    }
	
	private ProductDto convertToDto(Product product) {
        return  modelMapper.map(product, ProductDto.class);    
    }
	
	private Cart convertToEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);         
    }
}