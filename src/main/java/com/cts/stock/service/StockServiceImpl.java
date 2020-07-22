package com.cts.stock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cts.stock.exception.StockAlreadyExistsException;
import com.cts.stock.exception.StockNotFoundException;
import com.cts.stock.model.Stock;
import com.cts.stock.repository.StockRepository;
import com.cts.supplier.invoice.service.SupplierInvoiceServiceImpl;

@Service
public class StockServiceImpl implements StockService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);
	
	private StockRepository StockRepository;
	
	@Autowired
	public StockServiceImpl(StockRepository StockRepository) {
		this.StockRepository = StockRepository;
	}

	@Override
	public Stock registerStock(Stock stock) throws StockAlreadyExistsException {
		stock = StockRepository.save(stock);
		if (stock == null) {
			throw new StockAlreadyExistsException("stock already exist");
		} else {
			return stock;
		}
	}

	@Override
	public Stock updateStock(String stockId, Stock stock) throws StockNotFoundException {
		StockRepository.save(stock);
		return StockRepository.findById(stockId).get();

	}

	@Override
	public boolean deleteStock(String stockId) throws StockNotFoundException {
		try {
			StockRepository.deleteById(stockId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Stock getStockById(String stockId) throws StockNotFoundException {
		
		return StockRepository.findById(stockId).get();
	}

	
}
