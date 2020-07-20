package com.cts.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.product.exception.ProductAlreadyExistsException;
import com.cts.product.exception.ProductNotFoundException;
import com.cts.product.model.Product;
import com.cts.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product registerProduct(Product product) throws ProductAlreadyExistsException {
		product = productRepository.save(product);
		if (product == null) {
			throw new ProductAlreadyExistsException("User already exist");
		} else {
			return product;
		}
	}

	@Override
	public Product updateProduct(String productId, Product product) throws ProductNotFoundException {
		productRepository.save(product);
		return productRepository.findById(productId).get();

	}

	@Override
	public boolean deleteProduct(String productId) throws ProductNotFoundException {
		try {
			productRepository.deleteById(productId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Product getProductById(String productId) throws ProductNotFoundException {
		Optional<Product> prd = productRepository.findById(productId);
		return prd.get();
	}
}
