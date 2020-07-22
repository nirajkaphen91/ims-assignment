package com.cts.customer.invoice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerInvoiceController.class);
	private CustomerInvoiceService customerInvoiceService;

	@Autowired
	public CustomerInvoiceController(CustomerInvoiceService customerInvoiceService) {
		this.customerInvoiceService = customerInvoiceService;
	}

	@PostMapping("/customerinvoice/register")
	public ResponseEntity<CustomerInvoice> createCustomerInvoice(@RequestBody CustomerInvoice customerInvoice) {
		try {
			customerInvoiceService.registerCustomerInvoice(customerInvoice);
			LOGGER.info("Customer invoice created for  bill no {}", customerInvoice.getBillno());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<CustomerInvoice>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CustomerInvoice>(customerInvoice, HttpStatus.CREATED);
	}

	@PutMapping("/customerinvoice/{billno}")
	public ResponseEntity<CustomerInvoice> updateProduct(@PathVariable String billno,
			@RequestBody CustomerInvoice customerInvoice) {
		try {
			customerInvoice = customerInvoiceService.updateCustomerInvoice(billno, customerInvoice);
			LOGGER.info("Customer invoice updated for  bill no {}", customerInvoice.getBillno());
			if (customerInvoice == null) {
				return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<CustomerInvoice>(customerInvoice, HttpStatus.OK);
			}
		} catch (CustomerInvoiceNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/customerinvoice/{billno}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String billno) {
		try {
			boolean flag = customerInvoiceService.deleteCustomerInvoice(billno);

			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (CustomerInvoiceNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/customerinvoice/{billno}")
	public ResponseEntity<CustomerInvoice> getProductById(@PathVariable String billno) {
		try {
			CustomerInvoice customerInvoice = customerInvoiceService.getCustomerInvoiceById(billno);
			// LOGGER.info("Customer invoice fetched for bill no {}",
			// customerInvoice.getBillno());
			if (customerInvoice == null) {
				return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<CustomerInvoice>(customerInvoice, HttpStatus.OK);
			}

		} catch (CustomerInvoiceNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<CustomerInvoice>(HttpStatus.NOT_FOUND);
		}
	}

}
