package com.cts.supplier.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Supplier {
	@Id
	private String sid;
	private String suppliername;
	private String suppliercompany;
	private String supplieraddress;

	public Supplier() {
	}

	public Supplier(String sid, String suppliername, String suppliercompany, String supplieraddress) {
		super();
		this.sid = sid;
		this.suppliername = suppliername;
		this.suppliercompany = suppliercompany;
		this.supplieraddress = supplieraddress;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getSuppliercompany() {
		return suppliercompany;
	}

	public void setSuppliercompany(String suppliercompany) {
		this.suppliercompany = suppliercompany;
	}

	public String getSupplieraddress() {
		return supplieraddress;
	}

	public void setSupplieraddress(String supplieraddress) {
		this.supplieraddress = supplieraddress;
	}

	@Override
	public String toString() {
		return "Supplier [sid=" + sid + ", suppliername=" + suppliername + ", suppliercompany=" + suppliercompany
				+ ", supplieraddress=" + supplieraddress + "]";
	}

}
