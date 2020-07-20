package com.cts.stock.service;

import com.cts.stock.exception.StockAlreadyExistsException;
import com.cts.stock.exception.StockNotFoundException;
import com.cts.stock.model.Stock;



public interface StockService {
	
	Stock registerStock(Stock stock) throws StockAlreadyExistsException;

	Stock updateStock(String stockId, Stock stock) throws StockNotFoundException;

	boolean deleteStock(String stockId) throws StockNotFoundException;

	Stock getStockById(String stockId) throws StockNotFoundException;

}
