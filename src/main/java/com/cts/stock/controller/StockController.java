package com.cts.stock.controller;

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

import com.cts.stock.exception.StockNotFoundException;
import com.cts.stock.model.Stock;
import com.cts.stock.service.StockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class StockController {

	private StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@ApiOperation(value = "Register stock using post url: /stock/register")
	@PostMapping("/stock/register")
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		try {
			stockService.registerStock(stock);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Stock>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
	}

	@PutMapping("/stock/{id}")
	@ApiOperation(value = "Update update using url: /stock/id")
	public ResponseEntity<Stock> updateStock(@PathVariable String stockId, @RequestBody Stock stock) {
		try {
			stock = stockService.updateStock(stockId, stock);
			if (stock == null) {
				return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Stock>(stock, HttpStatus.OK);
			}
		} catch (StockNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/stock/{id}")
	@ApiOperation(value = "Delete stock using url: /stock/id")
	public ResponseEntity<Void> deleteProduct(@PathVariable String stockId) {
		try {
			boolean flag = stockService.deleteStock(stockId);
			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (StockNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/stock/{stockId}")
	@ApiOperation(value = "Get product using url: /stock/id")
	public ResponseEntity<Stock> getStockById(@PathVariable String stockId) {
		try {
			Stock stock = stockService.getStockById(stockId);
			if (stock == null) {
				return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Stock>(stock, HttpStatus.OK);
			}

		} catch (StockNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
	}

}
