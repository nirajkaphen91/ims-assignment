package com.cts.customer.invoice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="customerinvoice")
public class CustomerInvoice {
	@Id
	private String billno;
	private Date billdate;
	private int billamount;
	private String customername;

	public CustomerInvoice() {
		this.billdate = new Date();
	}

	public CustomerInvoice(String billno, Date billdate, int billamount, String customername) {
		super();
		this.billno = billno;
		this.billdate = billdate;
		this.billamount = billamount;
		this.customername = customername;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public int getBillamount() {
		return billamount;
	}

	public void setBillamount(int billamount) {
		this.billamount = billamount;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Override
	public String toString() {
		return "CustomerInvoice [billno=" + billno + ", billdate=" + billdate + ", billamount=" + billamount
				+ ", customername=" + customername + "]";
	}


	
	
	
	
}
