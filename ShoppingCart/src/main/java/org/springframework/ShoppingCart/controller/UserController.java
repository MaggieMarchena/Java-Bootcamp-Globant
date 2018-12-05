package org.springframework.ShoppingCart.controller;

import java.util.List;

import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ShoppingCart/v1")
public class UserController{
	
	private UserService userService;
	
	@PostMapping("/user")
	public User add(@RequestBody User user) {
		return this.userService.add(user);
	}

	@GetMapping("/user/{id}")
	public User get(@PathVariable("id") Long userID) {
		return this.userService.get(userID);
	}
	
	@GetMapping("/user")
	public List<User> getAll() {
		return this.userService.getAll();
	}
	
	@PutMapping("/user//{id}/attributes/{firstName}")
	public User setFirstName(@PathVariable("id") Long id, @PathVariable("firstName") String firstName) {
		return this.userService.changeFirstName(id, firstName);
	}
	
	@PutMapping("/user//{id}/attributes/{lastName}")
	public User setLastName(@PathVariable("id") Long id, @PathVariable("lastName") String lastName) {
		return this.userService.changeFirstName(id, lastName);
	}

	@DeleteMapping("/user/{id}")
	public User delete(@PathVariable("id") long userID) {
		return this.userService.delete(userID);
	}
}