package com.devrezaur.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devrezaur.main.model.User;
import com.devrezaur.main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
}
