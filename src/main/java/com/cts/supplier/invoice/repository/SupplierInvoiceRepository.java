package com.cts.supplier.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.supplier.invoice.model.SupplierInvoice;

@Repository
public interface SupplierInvoiceRepository extends JpaRepository<SupplierInvoice, String> {

}
