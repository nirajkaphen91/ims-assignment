package com.cts.supplier.invoice.service;

import com.cts.supplier.invoice.exception.SupplierInvoiceAlreadyExistsException;
import com.cts.supplier.invoice.exception.SupplierInvoiceNotFoundException;
import com.cts.supplier.invoice.model.SupplierInvoice;

public interface SupplierInvoiceService {
	
	SupplierInvoice registerSupplierInvoice(SupplierInvoice supplierInvoice) throws SupplierInvoiceAlreadyExistsException;

	SupplierInvoice updateSupplierInvoice(String supplierInvoiceID, SupplierInvoice supplierInvoice) throws SupplierInvoiceNotFoundException;

	boolean deleteSupplierInvoice(String supplierInvoiceID) throws SupplierInvoiceNotFoundException;

	SupplierInvoice getSupplierInvoiceById(String supplierInvoiceID) throws SupplierInvoiceNotFoundException;

}
