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

@RestController
public class SupplierController {
	
	private SupplierService supplierService;

	@Autowired
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	
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
	
	@PutMapping("/supplier/{sid}")
	
	public ResponseEntity<Supplier> updateSupplier(@PathVariable String sid, @RequestBody Supplier supplier) {
		try {
			supplier = supplierService.updateSupplier(sid, supplier);
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

	@DeleteMapping("/supplier/{sid}")

	public ResponseEntity<Void> deleteSupplier(@PathVariable String sid) {
		try {
			boolean flag = supplierService.deleteSupplier(sid);
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

	@GetMapping("/supplier/{sid}")
	
	public ResponseEntity<Supplier> getSupplierById(@PathVariable String sid) {
		try {
			Supplier supplier = supplierService.getSupplierById(sid);
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
