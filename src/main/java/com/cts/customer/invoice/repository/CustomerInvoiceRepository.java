package com.cts.customer.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerInvoiceRepository extends JpaRepository<com.cts.customer.invoice.model.CustomerInvoice, String>  {

}
