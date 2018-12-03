package org.springframework.ShoppingCart.repository;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long>{
	
}