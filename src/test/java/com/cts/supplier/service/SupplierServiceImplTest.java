package com.cts.supplier.service;

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

import com.cts.supplier.exception.SupplierAlreadyExistsException;
import com.cts.supplier.exception.SupplierNotFoundException;
import com.cts.supplier.model.Supplier;
import com.cts.supplier.repository.SupplierRepository;

public class SupplierServiceImplTest {
	@Mock
	private SupplierRepository supplierRepository;
	@InjectMocks
	private SupplierServiceImpl SupplierService;

	private Supplier supplier;
	private List<Supplier> supplierList = null;
	private Optional<Supplier> options;

	/*
	 * private int supplierId; private String supplierName; private String
	 * supplierCompany; private String supplierAddress;
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		supplier = new Supplier();
		supplier.setSupplierId("sup001");
		supplier.setSupplierAdderss("Pune");
		supplier.setSupplierCompany("Anilam");
		supplier.setSupplierName("xyz");

		supplierList = new ArrayList<>();
		supplierList.add(supplier);
		options = Optional.of(supplier);
	}

	@Test
	public void testRegisterSupplier() throws SupplierAlreadyExistsException {
		when(supplierRepository.save((Supplier) any())).thenReturn(supplier);
		Supplier supplierSaved = SupplierService.registerSupplier(supplier);
		assertEquals(supplier, supplierSaved);
	}

	@Test
	public void testUpdateSupplier() throws SupplierNotFoundException {
		when(supplierRepository.findById(supplier.getSupplierId())).thenReturn(options);
		supplier.setSupplierAdderss("pune");
		Supplier fetchsupplier = SupplierService.updateSupplier(supplier.getSupplierId(), supplier);
		assertEquals(supplier, fetchsupplier);
	}

	@Test
	public void testDeleteSupplier() throws SupplierNotFoundException {
		when(supplierRepository.findById(supplier.getSupplierId())).thenReturn(options);
		boolean flag = SupplierService.deleteSupplier(supplier.getSupplierId());
		assertEquals(true, flag);
	}

	@Test
	public void testGetSupplierById() throws SupplierNotFoundException {
		when(supplierRepository.findById(supplier.getSupplierId())).thenReturn(options);
		Supplier fetchedsupplier = SupplierService.getSupplierById(supplier.getSupplierId());
		assertEquals(supplier, fetchedsupplier);
	}

	@Test
	public void testSupplierServiceImpl() {
		// fail("Not yet implemented");
	}

}
