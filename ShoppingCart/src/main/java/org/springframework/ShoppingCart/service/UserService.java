package org.springframework.ShoppingCart.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public User add(User user) throws IllegalArgumentException{
		if(user == null) {
			throw new IllegalArgumentException("User can't be null");
		}
		rwLock.writeLock().lock();
		try {
			return this.userRepository.save(user);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public User get(Long userID) throws IllegalArgumentException{
		rwLock.readLock().lock();
		try {
			Optional<User> user = this.userRepository.findById(userID);
			if(user.equals(Optional.empty())) {
				throw new IllegalArgumentException("User not found with Id: " + userID);
			}
			return user.get();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public List<User> getAll(){
		rwLock.readLock().lock();
		try {
			return (List<User>) userRepository.findAll();
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
	public User changeFirstName(Long userID, String firstName) throws IllegalArgumentException {
		User user = get(userID);
		rwLock.writeLock().lock();
		try {
			user.setFirstName(firstName);
			return userRepository.save(user);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public User changeLastName(Long userID, String lastName) throws IllegalArgumentException {
		User user = get(userID);
		rwLock.writeLock().lock();
		try {
			user.setLastName(lastName);
			return userRepository.save(user);
		}
		finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public User delete(Long userID) throws IllegalArgumentException {
		User delete = get(userID);
		rwLock.writeLock().lock();
		try {
			userRepository.delete(delete);
			return delete;
		}
		finally {
			rwLock.writeLock().lock();
		}
	}

}