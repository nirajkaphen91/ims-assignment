package com.cts.supplier.invoice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SupplierInvoice {
	
	@Id
	private String refrenceID;
	private String supplierID;
	private Date supplyDate;
	private String productID;
	private String productquantity;
	private String productPrice;

	public SupplierInvoice() {
		this.supplyDate = new Date();
	}

	public SupplierInvoice(String refrenceID, String supplierID, Date supplyDate, String productID,
			String productquantity, String productPrice) {
		super();
		this.refrenceID = refrenceID;
		this.supplierID = supplierID;
		this.supplyDate = supplyDate;
		this.productID = productID;
		this.productquantity = productquantity;
		this.productPrice = productPrice;
	}
	
	public String getRefrenceID() {
		return refrenceID;
	}

	public void setRefrenceID(String refrenceID) {
		this.refrenceID = refrenceID;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Date getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(String productquantity) {
		this.productquantity = productquantity;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "SupplierInvoice [refrenceID=" + refrenceID + ", supplierID=" + supplierID + ", supplyDate=" + supplyDate
				+ ", productID=" + productID + ", productquantity=" + productquantity + ", productPrice=" + productPrice
				+ "]";
	}
}
