package com.cts.stock.service;

import static org.junit.Assert.assertEquals;
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

import com.cts.stock.exception.StockAlreadyExistsException;
import com.cts.stock.exception.StockNotFoundException;
import com.cts.stock.model.Stock;
import com.cts.stock.repository.StockRepository;

public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepository;
	private Stock stock;
	@InjectMocks
	private StockServiceImpl StockService;
	private List<Stock> stockList = null;
	private Optional<Stock> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		stock = new Stock();
		stock.setProductId("prod001");
		stock.setQuantity(5);

		stockList = new ArrayList<>();
		stockList.add(stock);
		options = Optional.of(stock);
	}

	@Test
	public void testRegisterStock() throws StockAlreadyExistsException {
		when(stockRepository.save((Stock) any())).thenReturn(stock);
		Stock stockSaved = StockService.registerStock(stock);
		assertEquals(stock, stockSaved);
	}

	@Test
	public void testUpdateStock() throws StockNotFoundException {
		when(stockRepository.findById(stock.getProductId())).thenReturn(options);
		stock.setQuantity(5);
		Stock fetchedStock = StockService.updateStock(stock.getProductId(), stock);
		assertEquals(stock, fetchedStock);	
	}

	@Test
	public void testStockServiceImpl() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteStock() throws StockNotFoundException {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetStockById() {
		//fail("Not yet implemented");
	}

}
