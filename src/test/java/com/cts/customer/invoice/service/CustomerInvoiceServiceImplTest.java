package com.cts.customer.invoice.service;

import static org.junit.Assert.assertEquals;
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

import com.cts.customer.invoice.exception.CustomerInvoiceAlreadyExistsException;
import com.cts.customer.invoice.exception.CustomerInvoiceNotFoundException;
import com.cts.customer.invoice.model.CustomerInvoice;
import com.cts.customer.invoice.repository.CustomerInvoiceRepository;

public class CustomerInvoiceServiceImplTest {

	@Mock
	private CustomerInvoiceRepository customerInvoiceRepository;
	private CustomerInvoice customerInvoice;
	@InjectMocks
	private CustomerInvoiceServiceImpl customerInvoiceService;
	private List<CustomerInvoice> customerInvoiceList = null;
	private Optional<CustomerInvoice> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		customerInvoice = new CustomerInvoice();
		customerInvoice.setBilldate(new Date());
		customerInvoice.setBillamount(600);
		customerInvoice.setCustomername("johnpass");
		customerInvoice.setBillno("Aoc001");
		customerInvoiceList = new ArrayList<>();
		customerInvoiceList.add(customerInvoice);
		options = Optional.of(customerInvoice);
	}

	/*
	 * private String billno; private Date billdate; private String billamount;
	 * private String customername;
	 */

	// @Test
	// public void testCustomerInvoiceServiceImpl() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testRegisterCustomerInvoice() throws CustomerInvoiceAlreadyExistsException {
		when(customerInvoiceRepository.save((CustomerInvoice) any())).thenReturn(customerInvoice);
		CustomerInvoice customerInvoiceSaved = customerInvoiceService.registerCustomerInvoice(customerInvoice);
		assertEquals(customerInvoice, customerInvoiceSaved);
	}

	@Test
	public void testUpdateCustomerInvoice() throws CustomerInvoiceNotFoundException {
		// when(customerInvoiceRepository.findById(product.getPid())).thenReturn(options);
		when(customerInvoiceRepository.findById(customerInvoice.getBillno())).thenReturn(options);
		customerInvoice.setCustomername("johnpass");
		CustomerInvoice fetchinvoice = customerInvoiceService.updateCustomerInvoice(customerInvoice.getBillno(),
				customerInvoice);

		assertEquals(customerInvoice, fetchinvoice);
		// assertEquals(customerInvoice, fetchCustomerInvoice);
	}

	@Test
	public void testDeleteCustomerInvoice() throws CustomerInvoiceNotFoundException {
		when(customerInvoiceRepository.findById(customerInvoice.getBillno()));
		boolean flag = customerInvoiceService.deleteCustomerInvoice(customerInvoice.getBillno());
		assertEquals(false, flag);
	}

	@Test
	public void testGetCustomerInvoiceById() throws CustomerInvoiceNotFoundException {
		when(customerInvoiceRepository.findById(customerInvoice.getBillno())).thenReturn(options);
		CustomerInvoice fetchCustomerInvoice = customerInvoiceService
				.getCustomerInvoiceById(customerInvoice.getBillno());
		assertEquals(customerInvoice, fetchCustomerInvoice);
	}
}

//}
