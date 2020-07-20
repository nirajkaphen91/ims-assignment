package com.cts.customer.invoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customer.invoice.exception.CustomerInvoiceAlreadyExistsException;
import com.cts.customer.invoice.exception.CustomerInvoiceNotFoundException;
import com.cts.customer.invoice.model.CustomerInvoice;
import com.cts.customer.invoice.repository.CustomerInvoiceRepository;

@Service
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {

	private CustomerInvoiceRepository customerInvoiceRepository;

	@Autowired
	public CustomerInvoiceServiceImpl(CustomerInvoiceRepository customerInvoiceRepository) {
		this.customerInvoiceRepository = customerInvoiceRepository;
	}

	@Override
	public CustomerInvoice registerCustomerInvoice(CustomerInvoice customerInvoice)
			throws CustomerInvoiceAlreadyExistsException {

		customerInvoice = customerInvoiceRepository.save(customerInvoice);
		if (customerInvoice == null) {
			throw new CustomerInvoiceAlreadyExistsException("customerInvoice already exist");
		} else {
			return customerInvoice;
		}

	}

	@Override
	public CustomerInvoice updateCustomerInvoice(String customerInvoiceId, CustomerInvoice customerInvoice)
			throws CustomerInvoiceNotFoundException {
		customerInvoiceRepository.save(customerInvoice);
		return customerInvoiceRepository.findById(customerInvoiceId).get();

	}

	@Override
	public boolean deleteCustomerInvoice(String customerInvoiceId) throws CustomerInvoiceNotFoundException {
		try {
			customerInvoiceRepository.deleteById(customerInvoiceId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public CustomerInvoice getCustomerInvoiceById(String customerInvoiceId) throws CustomerInvoiceNotFoundException {
		Optional<CustomerInvoice> crd = customerInvoiceRepository.findById(customerInvoiceId);
		return crd.get();
	}

}
