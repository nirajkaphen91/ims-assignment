package com.cts.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.user.exceptions.UserAlreadyExistsException;
import com.cts.user.exceptions.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		return userRepository.findByUserIdAndUserPassword(userId, password);
	}
	
	public User registerUser(User user) throws UserAlreadyExistsException {
		user = userRepository.save(user);
		if(user == null) {
			throw new UserAlreadyExistsException("User already exist");
		} else {
			return user;
		}
	}

	public User updateUser(String userId,User user) throws UserNotFoundException {
		userRepository.save(user);
		return userRepository.findById(userId).get();
	}

	public boolean deleteUser(String userId) throws UserNotFoundException {
		try {
			userRepository.deleteById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public User getUserById(String userId) throws UserNotFoundException {
		return userRepository.findById(userId).get();
	}
}
