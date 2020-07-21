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
	 * private String sid;
	private String suppliername;
	private String suppliercompany;
	private String supplieraddress;
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		supplier = new Supplier();
		supplier.setSid("sup001");
		supplier.setSupplieraddress("Pune");
		supplier.setSuppliercompany("Anilam");
		supplier.setSuppliername("xyz");

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
		//when(supplierRepository.findById(supplier.getSId())).thenReturn(options);
		when (supplierRepository.findById(supplier.getSid())).thenReturn(options);
		
		supplier.setSupplieraddress("pune");
		Supplier fetchsupplier = SupplierService.updateSupplier(supplier.getSid(), supplier);
		assertEquals(supplier, fetchsupplier);
	}

	@Test
	public void testDeleteSupplier() throws SupplierNotFoundException {
		when(supplierRepository.findById(supplier.getSid())).thenReturn(options);
		boolean flag = SupplierService.deleteSupplier(supplier.getSid());
		assertEquals(true, flag);
	}

	@Test
	public void testGetSupplierById() throws SupplierNotFoundException {
		when(supplierRepository.findById(supplier.getSid())).thenReturn(options);
		Supplier fetchedsupplier = SupplierService.getSupplierById(supplier.getSid());
		assertEquals(supplier, fetchedsupplier);
	}

	//@Test
	//public void testSupplierServiceImpl() {
		// fail("Not yet implemented");
	//}

}
