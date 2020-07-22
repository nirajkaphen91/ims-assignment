package com.cts.supplier.invoice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.supplier.invoice.exception.SupplierInvoiceAlreadyExistsException;
import com.cts.supplier.invoice.exception.SupplierInvoiceNotFoundException;
import com.cts.supplier.invoice.model.SupplierInvoice;
import com.cts.supplier.invoice.repository.SupplierInvoiceRepository;

@Service
public class SupplierInvoiceServiceImpl implements SupplierInvoiceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierInvoiceServiceImpl.class);

	private SupplierInvoiceRepository supplierInvoiceRepository;

	@Autowired
	public SupplierInvoiceServiceImpl(SupplierInvoiceRepository supplierInvoiceRepository) {
		this.supplierInvoiceRepository = supplierInvoiceRepository;
	}

	@Override
	public SupplierInvoice registerSupplierInvoice(SupplierInvoice supplierInvoice)
			throws SupplierInvoiceAlreadyExistsException {

		supplierInvoice = supplierInvoiceRepository.save(supplierInvoice);

		if (supplierInvoice == null) {

			throw new SupplierInvoiceAlreadyExistsException("supplierInvoice already exist");
		} else {
			return supplierInvoice;
		}

	}

	@Override
	public SupplierInvoice updateSupplierInvoice(String supplierInvoiceID, SupplierInvoice supplierInvoice)
			throws SupplierInvoiceNotFoundException {

		supplierInvoiceRepository.save(supplierInvoice);
		return supplierInvoiceRepository.findById(supplierInvoiceID).get();

	}

	@Override
	public boolean deleteSupplierInvoice(String supplierInvoiceID) throws SupplierInvoiceNotFoundException {
		try {
			supplierInvoiceRepository.deleteById(supplierInvoiceID);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public SupplierInvoice getSupplierInvoiceById(String supplierInvoiceID) throws SupplierInvoiceNotFoundException {
		Optional<SupplierInvoice> spr = supplierInvoiceRepository.findById(supplierInvoiceID);
		return spr.get();
	}

}
