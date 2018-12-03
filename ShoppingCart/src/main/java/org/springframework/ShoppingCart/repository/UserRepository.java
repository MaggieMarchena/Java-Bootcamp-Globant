package org.springframework.ShoppingCart.repository;

import org.springframework.ShoppingCart.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	
}