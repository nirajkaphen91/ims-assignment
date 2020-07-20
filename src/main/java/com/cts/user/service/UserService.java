package com.cts.user.service;

import com.cts.user.exceptions.UserAlreadyExistsException;
import com.cts.user.exceptions.UserNotFoundException;
import com.cts.user.model.User;

public interface UserService {
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

	User registerUser(User user) throws UserAlreadyExistsException;

	User updateUser(String userId, User user) throws UserNotFoundException;

	boolean deleteUser(String userId) throws UserNotFoundException;

	User getUserById(String userId) throws UserNotFoundException;
}
