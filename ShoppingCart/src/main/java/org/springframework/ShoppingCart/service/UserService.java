package org.springframework.ShoppingCart.service;

import java.util.List;

import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public User add(User user) {
		userRepository.save(user);
		return user;
	}
	
	public User get(Long userID) {
		return userRepository.findById(userID).get();
	}
	
	public List<User> getAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public User changeFirstName(Long userID, String firstName) {
		User user = get(userID);
		user.setFirstName(firstName);
		return userRepository.save(user);
	}
	
	public User changeLastName(Long userID, String lastName) {
		User user = get(userID);
		user.setLastName(lastName);
		return userRepository.save(user);
	}
	
	public User delete(Long userID) {
		User delete = get(userID);
		userRepository.delete(delete);
		return delete;
	}

}