package org.springframework.ShoppingCart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.ShoppingCart.model.Cart;
import org.springframework.ShoppingCart.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService{
	
	private Map<Long, User> users = new HashMap<>();
	private AtomicLong counter = new AtomicLong();
	
	public User add(User user) {
		if (user.getId() == null) {
			user.setId(counter.incrementAndGet());
		}
		this.users.put(user.getId(), user);
		return user;
	}
	
	public User get(Long userID) {
		if (this.users.containsKey(userID)){
			return this.users.get(userID);
		}
		return null;
	}
	
	public List<User> getAll(){
		return (List<User>) this.users.values();
	}
	
	public User delete(Long userID) {
		User delete = this.get(userID);
		if(delete != null) {
			this.users.remove(userID);
			return delete;
		}
		return null;
	}

}