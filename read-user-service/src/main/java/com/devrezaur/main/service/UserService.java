package com.devrezaur.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devrezaur.main.model.User;
import com.devrezaur.main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
}
