package com.cts.customer.invoice.exception;

public class CustomerInvoiceAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CustomerInvoiceAlreadyExistsException(String message) {
		super(message);
	}
}
