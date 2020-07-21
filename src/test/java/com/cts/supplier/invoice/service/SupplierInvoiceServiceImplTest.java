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
		supplierInvoice.setSupplierid("CTS234");

		supplierInvoice.setRefid("NOC91");
		supplierInvoice.setProductprice(90);
		supplierInvoice.setProductquantity("86");
		supplierInvoice.setProductid("265ABC");

		SupplierInvoiceList = new ArrayList<>();
		SupplierInvoiceList.add(supplierInvoice);
		options = Optional.of(supplierInvoice);
	}

	@Test
	public void testRegisterSupplierInvoice() throws SupplierInvoiceAlreadyExistsException {
		when(supplierInvoiceRepository.save((SupplierInvoice) any())).thenReturn(supplierInvoice);
		SupplierInvoice supplierInvoiceSaved = supplierInvoiceService.registerSupplierInvoice(supplierInvoice);
		assertEquals(supplierInvoice, supplierInvoiceSaved);
	}

	@Test
	public void testUpdateSupplierInvoice() throws SupplierInvoiceNotFoundException {
		when(supplierInvoiceRepository.findById(supplierInvoice.getProductid())).thenReturn(options);
		supplierInvoice.setProductprice(90);
		SupplierInvoice fetchsupplierInvoice = supplierInvoiceService.updateSupplierInvoice(supplierInvoice.getRefid(),
				supplierInvoice);
		assertEquals(supplierInvoice, fetchsupplierInvoice);
	}

	@Test
	public void testDeleteSupplierInvoice() throws SupplierInvoiceNotFoundException {
		when(supplierInvoiceRepository.findById(supplierInvoice.getProductid())).thenReturn(options);
		boolean flag = supplierInvoiceService.deleteSupplierInvoice(supplierInvoice.getProductid());
		assertEquals(true, flag);
		// boolean flag = userService.deleteUser(user.getUserId());
	}

	@Test
	public void testGetSupplierInvoiceById() throws SupplierInvoiceNotFoundException {
		when(supplierInvoiceRepository.findById(supplierInvoice.getProductid())).thenReturn(options);
		SupplierInvoice fetchedsupplierInvoice = supplierInvoiceService
				.getSupplierInvoiceById(supplierInvoice.getProductid());
		assertEquals(supplierInvoice, fetchedsupplierInvoice);

	}

}
