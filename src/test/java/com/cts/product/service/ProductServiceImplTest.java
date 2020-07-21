package com.cts.product.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.product.exception.ProductAlreadyExistsException;
import com.cts.product.exception.ProductNotFoundException;
import com.cts.product.model.Product;
import com.cts.product.repository.ProductRepository;

public class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;
	private Product product;
	@InjectMocks
	private ProductServiceImpl ProductService;
	private List<Product> productList = null;
	private Optional<Product> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		product = new Product();

		/*
		 * private String pid; private String name; private int price; private String
		 * pdescription;
		 */

		product.setPid("Sawn123");
		product.setName("Laptop");
//		
		product.setPrice(800);
//		product.setDescription("computerDevice");
		productList = new ArrayList<>();
		productList.add(product);
		options = Optional.of(product);
	}

	@Test
	public void testProductServiceImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegisterProduct() throws ProductAlreadyExistsException {

		when(productRepository.save((Product) any())).thenReturn(product);
		Product productSaved = ProductService.registerProduct(product);
		assertEquals(product, productSaved);
	}

	@Test
	public void testUpdateProduct() throws ProductNotFoundException {
		when(productRepository.findById(product.getPid())).thenReturn(options);
		product.setName("Laptop");
		Product fetchproduct = ProductService.updateProduct(product.getPid(), product);
		assertEquals(product, fetchproduct);
	}

	@Test
	public void testDeleteProduct() throws ProductNotFoundException {
		when(productRepository.findById(product.getPid())).thenReturn(options);
		boolean flag = ProductService.deleteProduct(product.getPid());
		assertEquals(true, flag);
		// boolean flag = userService.deleteUser(user.getUserId());
	}

	@Test
	public void testGetProductById() throws ProductNotFoundException {
		when(productRepository.findById(product.getPid())).thenReturn(options);
		Product fetchedProduct = ProductService.getProductById(product.getPid());
		assertEquals(product, fetchedProduct);

	}

}
