package org.springframework.ShoppingCart;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.service.CartService;
import org.springframework.ShoppingCart.service.ProductService;
import org.springframework.ShoppingCart.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(UserService userService, CartService cartService, ProductService productService) {
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		User u4 = new User();
		User u5 = new User();
		
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		Product p4 = new Product();
		Product p5 = new Product();
	
		Cart c1 = new Cart();
		Cart c2 = new Cart();
		
		return (args) -> {
			
			userService.add(u1);
			userService.changeFirstName(u1.getId(), "Dave");
			userService.changeLastName(u1.getId(), "Grohl");
			
			userService.add(u2);
			userService.changeFirstName(u2.getId(), "Taylor");
			userService.changeLastName(u2.getId(), "Hawkings");
			
			userService.add(u3);
			userService.changeFirstName(u3.getId(), "Chris");
			userService.changeLastName(u3.getId(), "Shiflett");
			
			userService.add(u4);
			userService.changeFirstName(u4.getId(), "Pat");
			userService.changeLastName(u4.getId(), "Smear");
			
			userService.add(u5);
			userService.changeFirstName(u5.getId(), "Nate");
			userService.changeLastName(u5.getId(), "Mendel");			
			
			
			productService.add(p1);
			productService.setName(p1.getId(), "Apple");
			productService.setPrice(p1.getId(), 5.50);
			
			productService.add(p2);
			productService.setName(p2.getId(), "Chair");
			productService.setPrice(p2.getId(), 47.00);
			
			productService.add(p3);
			productService.setName(p3.getId(), "Cheese");
			productService.setPrice(p3.getId(), 7.50);
			
			productService.add(p4);
			productService.setName(p4.getId(), "Flour");
			productService.setPrice(p4.getId(), 3.40);
			
			productService.add(p5);
			productService.setName(p5.getId(), "Bag");
			productService.setPrice(p5.getId(), 4.20);
			
			cartService.addCart(c1);
			cartService.addCart(c2);
			
			cartService.setUser(c1.getId(), u1.getId());
			cartService.setUser(c2.getId(), u2.getId());
			
			cartService.addProduct(c1.getId(), p1.getId());
			cartService.addProduct(c1.getId(), p2.getId());
			
			cartService.addProduct(c2.getId(), p3.getId());
			cartService.addProduct(c2.getId(), p3.getId());
			cartService.addProduct(c2.getId(), p4.getId());
			cartService.addProduct(c2.getId(), p5.getId());
		};
	}

}