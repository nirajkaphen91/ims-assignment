package com.cts.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.supplier.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, String> {

}
