package org.springframework.ShoppingCart;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.Product;
import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.repository.CartRepository;
import org.springframework.ShoppingCart.repository.ProductRepository;
import org.springframework.ShoppingCart.repository.UserRepository;
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
	public CommandLineRunner demo(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository) {
		
		User u1 = new User(1L, "Dave Ghrol");
		User u2 = new User(2L, "Taylor Hawkings");
		User u3 = new User(3L, "Chris Shiflett");
		User u4 = new User(4L, "Pat Smear");
		User u5 = new User(5L, "Nate Mendel");
		
		Product p1 = new Product("Apple", 5.50);
		Product p2 = new Product("Chair", 47.00);
		Product p3 = new Product("Cheese", 7.50);
		Product p4 = new Product("Flour", 3.40);
		Product p5 = new Product("Bag", 0.20);
		
		Cart c1 = new Cart(u1);
		Cart c2 = new Cart(u2);
		
		c1.addProduct(p1);
		c1.addProduct(p2);
		
		c2.addProduct(p3);
		c2.addProduct(p3);
		c2.addProduct(p4);
		c2.addProduct(p5);
		
		return (args) -> {
			userRepository.save(u1);
			userRepository.save(u2);
			userRepository.save(u3);
			userRepository.save(u4);
			userRepository.save(u5);
			
			productRepository.save(p1);
			productRepository.save(p2);
			productRepository.save(p3);
			productRepository.save(p4);
			productRepository.save(p5);
			
			cartRepository.save(c1);
			cartRepository.save(c2);
		};
	}

}