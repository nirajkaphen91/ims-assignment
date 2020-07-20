//package com.cts.user.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.cts.user.exceptions.UserAlreadyExistsException;
//import com.cts.user.exceptions.UserNotFoundException;
//import com.cts.user.model.User;
//import com.cts.user.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class UserControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//	@MockBean
//	private User user;
//	@MockBean
//	private UserService userService;
//	@InjectMocks
//	private UserController userController;
//
//	@Before
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//		user = new User();
//		user.setUserId("Jhon123");
//		user.setFirstName("Jhon Simon");
//		user.setUserPassword("123456");
//		user.setUserAddedDate(new Date());
//	}
//
//	@Test
//	public void registerUserSuccess() throws Exception {
//		when(userService.registerUser(user)).thenReturn(user);
//		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//
//	}
//
//	@Test
//	public void registerUserFailure() throws Exception {
//		when(userService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
//		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//
//	}
//
//	@Test
//	public void updateUserSuccess() throws Exception {
//		user.setUserPassword("23456789");
//		when(userService.updateUser(eq(user.getUserId()), any())).thenReturn(user);
//		mockMvc.perform(put("/user/Jhon123").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void updateUserFailure() throws Exception {
//		user.setUserPassword("23456789");
//		when(userService.updateUser(eq(user.getUserId()), any())).thenThrow(UserNotFoundException.class);
//		mockMvc.perform(put("/user/Jhon123").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void deleteUserSuccess() throws Exception {
//		when(userService.deleteUser("Jhon123")).thenReturn(true);
//		mockMvc.perform(delete("/user/Jhon123").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void deleteUserFailure() throws Exception {
//		when(userService.deleteUser("Jhon123")).thenThrow(UserNotFoundException.class);
//		mockMvc.perform(delete("/user/Jhon123").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void getByUserIdSuccess() throws Exception {
//
//		when(userService.getUserById("Jhon123")).thenReturn(user);
//		mockMvc.perform(get("/user/Jhon123").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void getByUserIdFAilure() throws Exception {
//		when(userService.getUserById("Jhon123")).thenThrow(UserNotFoundException.class);
//		mockMvc.perform(get("/user/Jhon123").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	public static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//}