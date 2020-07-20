package com.cts.supplier.invoice.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.cts.supplier.invoice.exception.SupplierInvoiceAlreadyExistsException;
import com.cts.supplier.invoice.exception.SupplierInvoiceNotFoundException;
import com.cts.supplier.invoice.model.SupplierInvoice;
import com.cts.supplier.invoice.repository.SupplierInvoiceRepository;

public class SupplierInvoiceServiceImplTest {
	
	
	@Mock
	private SupplierInvoiceRepository supplierInvoiceRepository;
	private SupplierInvoice supplierInvoice;
	@InjectMocks
	private SupplierInvoiceServiceImpl supplierInvoiceService;
	private List<SupplierInvoice> SupplierInvoiceList = null;
	private Optional<SupplierInvoice> options;
	

	@Test
	public void testSupplierInvoiceServiceImpl() {
		fail("Not yet implemented");
	}
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		supplierInvoice = new SupplierInvoice();
		supplierInvoice.setSupplyDate(new Date());
		supplierInvoice.setSupplierID("CTS234");
		
		
		supplierInvoice.setRefrenceID("NK91");
		supplierInvoice.setProductPrice("90");
		supplierInvoice.setProductquantity("86");
		supplierInvoice.setProductID("265ABC");
		
		SupplierInvoiceList = new ArrayList<>();
		SupplierInvoiceList.add(supplierInvoice);
		options = Optional.of(supplierInvoice);
	}
	

	@Test
	public void testRegisterSupplierInvoice()throws SupplierInvoiceAlreadyExistsException {
		when(supplierInvoiceRepository.save((SupplierInvoice) any())).thenReturn(supplierInvoice);
		SupplierInvoice supplierInvoiceSaved = supplierInvoiceService.registerSupplierInvoice(supplierInvoice);
		assertEquals(supplierInvoice, supplierInvoiceSaved);
	}

	@Test
	public void testUpdateSupplierInvoice()throws SupplierInvoiceNotFoundException {
		when(supplierInvoiceRepository.findById(supplierInvoice.getProductID())).thenReturn(options);
		supplierInvoice.setProductPrice("100");
		SupplierInvoice fetchsupplierInvoice = supplierInvoiceService.updateSupplierInvoice(supplierInvoice.getRefrenceID(), supplierInvoice);
		assertEquals(supplierInvoice, fetchsupplierInvoice);
	}

	@Test
	public void testDeleteSupplierInvoice()throws SupplierInvoiceNotFoundException {
		when(supplierInvoiceRepository.findById(supplierInvoice.getProductID())).thenReturn(options);
		boolean flag = supplierInvoiceService.deleteSupplierInvoice(supplierInvoice.getProductID());
		assertEquals(true, flag);
		//boolean flag = userService.deleteUser(user.getUserId());
	}

	@Test
	public void testGetSupplierInvoiceById() throws SupplierInvoiceNotFoundException {
		when(supplierInvoiceRepository.findById(supplierInvoice.getProductID())).thenReturn(options);
		SupplierInvoice fetchedsupplierInvoice= supplierInvoiceService.getSupplierInvoiceById(supplierInvoice.getProductID());
		assertEquals(supplierInvoice, fetchedsupplierInvoice);
		
	}

}
