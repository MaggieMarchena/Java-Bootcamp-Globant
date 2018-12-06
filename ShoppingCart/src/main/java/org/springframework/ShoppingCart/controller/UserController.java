package org.springframework.ShoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ShoppingCart.dto.UserDto;
import org.springframework.ShoppingCart.model.User;
import org.springframework.ShoppingCart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserService userService;
	@Autowired
    private ModelMapper modelMapper;
	
	@PostMapping("/user")
	public UserDto add(@RequestBody UserDto user) {
		return this.convertToDto(this.userService.add(this.convertToEntity(user)));
	}

	@GetMapping("/user/{id}")
	public UserDto get(@PathVariable("id") Long userID) {
		return this.convertToDto(this.userService.get(userID));
	}
	
	@GetMapping("/user")
	public List<UserDto> getAll() {
		List<User> users = this.userService.getAll();
		List<UserDto> result = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			result.add(this.convertToDto(users.get(i)));
		}
		return result;
	}
	
	@PutMapping("/user//{id}/attributes/{firstName}")
	public UserDto setFirstName(@PathVariable("id") Long id, @PathVariable("firstName") String firstName) {
		return this.convertToDto(this.userService.changeFirstName(id, firstName));
	}
	
	@PutMapping("/user//{id}/attributes/{lastName}")
	public UserDto setLastName(@PathVariable("id") Long id, @PathVariable("lastName") String lastName) {
		return this.convertToDto(this.userService.changeFirstName(id, lastName));
	}

	@DeleteMapping("/user/{id}")
	public UserDto delete(@PathVariable("id") long userID) {
		return this.convertToDto(this.userService.delete(userID));
	}
	
	//Conversion
	private UserDto convertToDto(User user) {
        return  modelMapper.map(user, UserDto.class);    
    }
	
	private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);         
    }
}