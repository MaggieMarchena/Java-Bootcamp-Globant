package org.springframework.ShoppingCart;

import org.springframework.ShoppingCart.model.User;
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
	public CommandLineRunner demoUser(UserRepository repository) {
		return (args) -> {
			repository.save(new User(1L, "Dave Ghrol"));
		};
	}
}
