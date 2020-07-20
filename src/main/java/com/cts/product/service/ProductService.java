package com.cts.product.service;

import com.cts.product.exception.ProductAlreadyExistsException;
import com.cts.product.exception.ProductNotFoundException;
import com.cts.product.model.Product;

public interface ProductService {
	Product registerProduct(Product product) throws ProductAlreadyExistsException;

	Product updateProduct(String productId, Product product) throws ProductNotFoundException;

	boolean deleteProduct(String productId) throws ProductNotFoundException;

	Product getProductById(String productId) throws ProductNotFoundException;
}
