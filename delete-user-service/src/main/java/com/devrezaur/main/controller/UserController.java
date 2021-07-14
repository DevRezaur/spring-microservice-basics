package com.devrezaur.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devrezaur.main.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/delete/{id}")
	public String updateUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception ex) {
			return "Cannot Perform Delete Operation";
		}
		return "Successfully Deleted";
	}
	
}
