package org.springframework.ShoppingCart.repository;

import org.springframework.ShoppingCart.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
}