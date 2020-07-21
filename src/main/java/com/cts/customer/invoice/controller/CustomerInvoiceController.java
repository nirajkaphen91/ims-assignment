package com.cts.customer.invoice.controller;


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

import com.cts.customer.invoice.exception.CustomerInvoiceNotFoundException;
import com.cts.customer.invoice.model.CustomerInvoice;
import com.cts.customer.invoice.service.CustomerInvoiceService;

@RestController

public class CustomerInvoiceController {
	private CustomerInvoiceService customerInvoiceService;

	@Autowired
	public CustomerInvoiceController(CustomerInvoiceService customerInvoiceService) {
		this.customerInvoiceService = customerInvoiceService;
	}

	@PostMapping("/customerInvoice/register")
	public ResponseEntity<CustomerInvoice> createCustomerInvoice(@RequestBody CustomerInvoice customerInvoice) {
		try {
			customerInvoiceService.registerCustomerInvoice(customerInvoice);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<CustomerInvoice>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CustomerInvoice>(customerInvoice, HttpStatus.CREATED);
	}

	@PutMapping("/customerInvoice/{id}")
	public ResponseEntity<CustomerInvoice> updateProduct(@PathVariable String customerInvoiceId,
			@RequestBody CustomerInvoice customerInvoice) {
		try {
			customerInvoice = customerInvoiceService.updateCustomerInvoice(customerInvoiceId, customerInvoice);
			if (customerInvoice == null) {
				return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<CustomerInvoice>(customerInvoice, HttpStatus.OK);
			}
		} catch (CustomerInvoiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/customerInvoice/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String customerInvoiceId) {
		try {
			boolean flag = customerInvoiceService.deleteCustomerInvoice(customerInvoiceId);
			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (CustomerInvoiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/customerInvoice/{customerInvoiceId}")
	public ResponseEntity<CustomerInvoice> getProductById(@PathVariable String customerInvoiceId) {
		try {
			CustomerInvoice customerInvoice = customerInvoiceService.getCustomerInvoiceById(customerInvoiceId);
			if (customerInvoice == null) {
				return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<CustomerInvoice>(customerInvoice, HttpStatus.OK);
			}

		} catch (CustomerInvoiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
		}
	}

}
