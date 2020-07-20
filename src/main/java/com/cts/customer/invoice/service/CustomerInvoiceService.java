package com.cts.customer.invoice.service;

import com.cts.customer.invoice.exception.CustomerInvoiceAlreadyExistsException;
import com.cts.customer.invoice.exception.CustomerInvoiceNotFoundException;
import com.cts.customer.invoice.model.CustomerInvoice;


public interface CustomerInvoiceService {
	
	CustomerInvoice registerCustomerInvoice(CustomerInvoice customerInvoice) throws CustomerInvoiceAlreadyExistsException;

	CustomerInvoice updateCustomerInvoice(String customerInvoiceId, CustomerInvoice customerInvoice) throws CustomerInvoiceNotFoundException;

	boolean deleteCustomerInvoice(String customerInvoiceId) throws CustomerInvoiceNotFoundException;

	CustomerInvoice getCustomerInvoiceById(String customerInvoiceId) throws CustomerInvoiceNotFoundException;

}
