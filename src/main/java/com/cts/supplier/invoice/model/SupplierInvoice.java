package com.cts.supplier.invoice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="supplierinvoice")
public class SupplierInvoice {
	
	@Id
	private String refid;
	private String supplierid;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date supplydate;
	private String productid;
	private String productquantity;
	private int productprice;

	public SupplierInvoice() {
		this.supplydate = new Date();
	}

	public SupplierInvoice(String refid, String supplierid, Date supplyDate, String productid, String productquantity,
			int productprice) {
		super();
		this.refid = refid;
		this.supplierid = supplierid;
		this.supplydate = supplyDate;
		this.productid = productid;
		this.productquantity = productquantity;
		this.productprice = productprice;
	}
	
	

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public Date getSupplyDate() {
		return supplydate;
	}

	public void setSupplyDate(Date supplyDate) {
		this.supplydate = supplyDate;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(String productquantity) {
		this.productquantity = productquantity;
	}

	public int getProductprice() {
		return productprice;
	}

	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}

	@Override
	public String toString() {
		return "SupplierInvoice [refid=" + refid + ", supplierid=" + supplierid + ", supplydate=" + supplydate
				+ ", productid=" + productid + ", productquantity=" + productquantity + ", productprice=" + productprice
				+ "]";
	}

	

	
}
