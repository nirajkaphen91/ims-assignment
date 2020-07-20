package com.cts.customer.invoice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CustomerInvoice {
	@Id
	private String billNo;
	private Date billdate;
	private String billAmount;
	private String customerName;

	public CustomerInvoice() {
		this.billdate = new Date();
	}
	
	public CustomerInvoice(String billNo, Date billdate, String billAmount, String customerName) {
		this.billNo = billNo;
		this.billdate = billdate;
		this.billAmount = billAmount;
		this.customerName = customerName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "CustomerInvoice [billNo=" + billNo + ", billdate=" + billdate + ", billAmount=" + billAmount
				+ ", CustomerName=" + customerName + "]";
	}

	
}
