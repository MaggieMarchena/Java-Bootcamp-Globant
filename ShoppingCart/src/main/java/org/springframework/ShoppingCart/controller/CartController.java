package org.springframework.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ShoppingCart.dto.CartDto;
import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.service.CartService;
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
public class CartController{
	
	@Autowired
	private CartService cartService;
	@Autowired
    private ModelMapper modelMapper;
	
	//Cart CRUD
	@PostMapping("/cart")
	public CartDto addCart(@RequestBody CartDto cart) {
		return this.convertToDto(this.cartService.addCart(this.convertToEntity(cart)));
	}
	
	@GetMapping("/cart/{id}")
	public CartDto getCart(@PathVariable("id") Long cartID) {
		return this.convertToDto(this.cartService.getCart(cartID));
	}
	
	@GetMapping("/cart")
	public List<CartDto> getAllCarts() {
		List<Cart> carts = this.cartService.getAllCarts();
		List<CartDto> result = new ArrayList<>();
		for(int i = 0; i < carts.size(); i++) {
        	result.add(this.convertToDto(carts.get(i)));
        }
        return result;   
	}
	
	@DeleteMapping("/cart/{id}")
	public CartDto removeCart(@PathVariable("id") Long cartID) {
		return this.convertToDto(this.cartService.deleteCart(cartID));
	}
	
	//User
	@PutMapping("/cart/{cartID}/user/{userID}")
	public CartDto setUser(@PathVariable("cartID") Long cartID, @PathVariable("userID") Long userID) {
		return this.convertToDto(this.cartService.setUser(cartID, userID));
	}
	
	//Products in cart
	@PutMapping("/cart/{cartID}/products/{productID}")
	public CartDto addProduct(@PathVariable("cartID") Long cartID, @PathVariable("productID") Long productID) {
		return this.convertToDto(this.cartService.addProduct(cartID, productID));
	}

	@DeleteMapping("/cart/{cartID}/products/{productID}")
	public CartDto removeProduct(@PathVariable("cartID") Long cartID, @PathVariable("productID") Long productID) {
		return this.convertToDto(this.cartService.removeProduct(cartID, productID));
	}
	
	//Conversion
	private CartDto convertToDto(Cart cart) {
        return  modelMapper.map(cart, CartDto.class);    
    }
	
	private Cart convertToEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);         
    }
}