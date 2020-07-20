package com.cts.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cts.stock.exception.StockAlreadyExistsException;
import com.cts.stock.exception.StockNotFoundException;
import com.cts.stock.model.Stock;
import com.cts.stock.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	
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
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Stock getStockById(String stockId) throws StockNotFoundException {
		// TODO Auto-generated method stub
		return StockRepository.findById(stockId).get();
	}

	
}
