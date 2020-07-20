package com.cts.supplier.controller;

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

import com.cts.supplier.exception.SupplierNotFoundException;
import com.cts.supplier.model.Supplier;
import com.cts.supplier.service.SupplierService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class SupplierController {
	
	private SupplierService supplierService;

	@Autowired
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	@ApiOperation(value = "Register supplier using post url: /supplier/register")
	@PostMapping("/supplier/register")
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
		try {
			supplierService.registerSupplier(supplier);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Supplier>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Supplier>(supplier, HttpStatus.CREATED);
	}
	
	@PutMapping("/supplier/{id}")
	@ApiOperation(value = "Update update using url: /supplier/id")
	public ResponseEntity<Supplier> updateSupplier(@PathVariable String supplierId, @RequestBody Supplier supplier) {
		try {
			supplier = supplierService.updateSupplier(supplierId, supplier);
			if (supplier == null) {
				return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
			}
		} catch (SupplierNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/supplier/{id}")
	@ApiOperation(value = "Delete supplier using url: /supplier/id")
	public ResponseEntity<Void> deleteSupplier(@PathVariable String supplierId) {
		try {
			boolean flag = supplierService.deleteSupplier(supplierId);
			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (SupplierNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/supplier/{supplierId}")
	@ApiOperation(value = "Get supplier using url: /supplier/supplierId")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable String supplierId) {
		try {
			Supplier supplier = supplierService.getSupplierById(supplierId);
			if (supplier == null) {
				return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
			}

		} catch (SupplierNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
