package com.cts.supplier.service;



import com.cts.supplier.exception.SupplierAlreadyExistsException;
import com.cts.supplier.exception.SupplierNotFoundException;
import com.cts.supplier.model.Supplier;

public interface SupplierService {
	
	Supplier registerSupplier(Supplier supplier) throws SupplierAlreadyExistsException;

	Supplier updateSupplier(String supplierId, Supplier supplier) throws SupplierNotFoundException;

	boolean deleteSupplier(String supplierId) throws SupplierNotFoundException;

	Supplier getSupplierById(String supplierId) throws SupplierNotFoundException;


}
