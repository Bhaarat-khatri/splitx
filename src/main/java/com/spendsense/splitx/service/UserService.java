package com.spendsense.splitx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(long id) {
		return userRepository.findById(id).get();
	}
	
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    } 
	
}
