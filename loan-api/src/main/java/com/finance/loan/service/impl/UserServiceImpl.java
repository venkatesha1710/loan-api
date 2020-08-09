package com.finance.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.loan.exception.UserNotFoundException;
import com.finance.loan.model.User;
import com.finance.loan.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	public User findByUserNameAndPassWord(String username, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsernameAndPassword(username, password);
		if(user==null) {
			throw new UserNotFoundException("User details not found");
		}
		return user;
	}
}
