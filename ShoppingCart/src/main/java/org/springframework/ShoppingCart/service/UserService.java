package org.springframework.ShoppingCart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public User add(User user) {
		return this.userRepository.save(user);
	}
	
	public User get(Long userID) throws IllegalArgumentException{
		Optional<User> user = this.userRepository.findById(userID);
		if(user.equals(Optional.empty())) {
			throw new IllegalArgumentException("User not found with Id: " + userID);
		}
		return user.get();
	}
	
	public List<User> getAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public User changeFirstName(Long userID, String firstName) throws IllegalArgumentException {
		User user = get(userID);
		user.setFirstName(firstName);
		return userRepository.save(user);
	}
	
	public User changeLastName(Long userID, String lastName) throws IllegalArgumentException {
		User user = get(userID);
		user.setLastName(lastName);
		return userRepository.save(user);
	}
	
	public User delete(Long userID) throws IllegalArgumentException {
		User delete = get(userID);
		userRepository.delete(delete);
		return delete;
	}

}