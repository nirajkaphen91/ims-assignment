package com.cts.supplier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.supplier.exception.SupplierAlreadyExistsException;
import com.cts.supplier.exception.SupplierNotFoundException;
import com.cts.supplier.model.Supplier;
import com.cts.supplier.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	private SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Supplier registerSupplier(Supplier supplier) throws SupplierAlreadyExistsException {
		supplier = supplierRepository.save(supplier);
		if (supplier == null) {
			throw new SupplierAlreadyExistsException("stock already exist");
		} else {
			return supplier;
		}
	}

	@Override
	public Supplier updateSupplier(String supplierId, Supplier supplier) throws SupplierNotFoundException {
		supplierRepository.save(supplier);
		return supplierRepository.findById(supplierId).get();
	}

	@Override
	public boolean deleteSupplier(String supplierId) throws SupplierNotFoundException {
		try {
			supplierRepository.deleteById(supplierId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Supplier getSupplierById(String supplierId) throws SupplierNotFoundException {
		Optional <Supplier> spl = supplierRepository.findById(supplierId);
		return spl.get();
		
		
		
	}

}
