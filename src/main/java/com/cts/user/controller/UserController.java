package com.cts.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.user.exceptions.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Register user using post url: /user/register")
	@PostMapping("/user/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			userService.registerUser(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Login to auth service using post url: /user/login")
	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {
		Map<String, String> map = new HashMap<>();

		String jwtToken = "";

		try {
			jwtToken = getToken(user.getUserId(), user.getUserPassword());
			map.clear();
			map.put("message", "Successfully logged in ...");
			map.put("token", jwtToken);
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("token", null);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	@ApiOperation(value = "Update user using url: /user/id")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		try {
			user = userService.updateUser(id, user);

			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "Delete user using url: /user/id")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {

		try {
			boolean flag = userService.deleteUser(id);

			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{id}")
	@ApiOperation(value = "Get user using url: /user/id")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		try {
			User user = userService.getUserById(id);
			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}

		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
	
	public String getToken(String username, String password) throws Exception {

		if (username == null || password == null) {
			throw new ServletException("Please provide valid username and password");
		}

		User user = userService.findByUserIdAndPassword(username, password);

		boolean flag = user != null ? true : false;

		if (!flag) {
			throw new ServletException("Either username or password is wrong.");
		}

		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;
	}
}
