package com.cts.supplier.invoice.controller;

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


import com.cts.supplier.invoice.exception.SupplierInvoiceNotFoundException;
import com.cts.supplier.invoice.model.SupplierInvoice;
import com.cts.supplier.invoice.service.SupplierInvoiceService;

@RestController
public class SupplierInvoiceController {
	
	private SupplierInvoiceService supplierInvoiceService;
	
	@Autowired
	public SupplierInvoiceController(SupplierInvoiceService supplierInvoiceService) {
		this.supplierInvoiceService = supplierInvoiceService;
	}
	
	@PostMapping("/supplierinvoice/register")
	public ResponseEntity<SupplierInvoice> createSupplierInvoice(@RequestBody SupplierInvoice supplierInvoice) {
		try {
			supplierInvoiceService.registerSupplierInvoice(supplierInvoice);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<SupplierInvoice>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<SupplierInvoice>(supplierInvoice, HttpStatus.CREATED);
	}
	
	@PutMapping("/supplierinvoice/{sid}")
	public ResponseEntity<SupplierInvoice> updateProduct(@PathVariable String sid, @RequestBody SupplierInvoice supplierInvoice) {
		try {
			supplierInvoice = supplierInvoiceService.updateSupplierInvoice(sid, supplierInvoice);
			if (supplierInvoice == null) {
				return new ResponseEntity<SupplierInvoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<SupplierInvoice>(supplierInvoice, HttpStatus.OK);
			}
		} catch (SupplierInvoiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<SupplierInvoice>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/supplierinvoice/{sid}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String sid) {
		try {
			boolean flag = supplierInvoiceService.deleteSupplierInvoice(sid);
			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (SupplierInvoiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/supplierinvoice/{sid}")
	public ResponseEntity<SupplierInvoice> getProductById(@PathVariable String sid) {
		try {
			SupplierInvoice supplierInvoice = supplierInvoiceService.getSupplierInvoiceById(sid);
					//(supplierID);
			if (supplierInvoice == null) {
				return new ResponseEntity<SupplierInvoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<SupplierInvoice>(supplierInvoice, HttpStatus.OK);
			}

		} catch (SupplierInvoiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<SupplierInvoice>(HttpStatus.NOT_FOUND);
		}
	}
}
