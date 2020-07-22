package com.cts.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer.invoice.controller.CustomerInvoiceController;
import com.cts.product.exception.ProductNotFoundException;
import com.cts.product.model.Product;
import com.cts.product.service.ProductService;

@RestController
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerInvoiceController.class);
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/product/register")
	public ResponseEntity<Product> createUser(@RequestBody Product product) {
		try {
			productService.registerProduct(product);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<Product>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@PutMapping("/product/{pid}")
	public ResponseEntity<Product> updateProduct(@PathVariable String pid, @RequestBody Product product) {
		try {
			product = productService.updateProduct(pid, product);
			if (product == null) {
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			}
		} catch (ProductNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/product/{pid}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String pid) {
		try {
			boolean flag = productService.deleteProduct(pid);
			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (ProductNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		try {
			Product product = productService.getProductById(productId);
			if (product == null) {
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			}

		} catch (ProductNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
}
