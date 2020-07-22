package com.cts.product.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.product.exception.ProductAlreadyExistsException;
import com.cts.product.exception.ProductNotFoundException;
import com.cts.product.model.Product;
import com.cts.product.repository.ProductRepository;
import com.cts.stock.service.StockServiceImpl;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
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
			LOGGER.error(e.getMessage());
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
