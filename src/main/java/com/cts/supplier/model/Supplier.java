package com.cts.supplier.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Supplier {
	@Id
	private String supplierId;
	private String supplierName;
	private String supplierCompany;
	private String supplierAddress;

	public Supplier() {
	}

	public Supplier(String supplierId, String supplierName, String supplierCompany, String supplydDate) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierCompany = supplierCompany;
		this.supplierAddress = supplydDate;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCompany() {
		return supplierCompany;
	}

	public void setSupplierCompany(String supplierCompany) {
		this.supplierCompany = supplierCompany;
	}

	public String getSupplierAdderss() {
		return supplierAddress;
	}

	public void setSupplierAdderss(String String) {
		this.supplierAddress = String;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierCompany="
				+ supplierCompany + ", supplydDate=" + supplierAddress + "]";
	}

}
