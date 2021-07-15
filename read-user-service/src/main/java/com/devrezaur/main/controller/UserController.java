package com.devrezaur.main.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devrezaur.main.model.User;
import com.devrezaur.main.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/read/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/read/all")
	public List<User> getAll() {
		return userService.getAll();
	}
	
}
