package com.cts.user.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.user.exceptions.UserAlreadyExistsException;
import com.cts.user.exceptions.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.repository.UserRepository;

public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userService;
	
	private User user;
	private List<User> userList = null;
	private Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setUserAddedDate(new Date());
		user.setUserId("John123");
		user.setUserPassword("johnpass");
		
		userList = new ArrayList<>();
		userList.add(user);
		options = Optional.of(user);
	}

	@Test
	public void registerUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.save((User) any())).thenReturn(user);
		User userSaved = userService.registerUser(user);
		assertEquals(user, userSaved);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void registerUserFailure() throws UserAlreadyExistsException {
		when(userRepository.save((User) any())).thenReturn(null);
		User userSaved = userService.registerUser(user);
		assertEquals(user, userSaved);
	}

	@Test
	public void updateUser() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		user.setFirstName("Niraj001");
		User fetchuser = userService.updateUser(user.getUserId(), user);
		assertEquals(user, fetchuser);
	}

	@Test
	public void deleteUserSuccess() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		boolean flag = userService.deleteUser(user.getUserId());
		assertEquals(true, flag);
	}

	@Test
	public void getUserById() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		User fetchedUser = userService.getUserById(user.getUserId());
		assertEquals(user, fetchedUser);
	}
}