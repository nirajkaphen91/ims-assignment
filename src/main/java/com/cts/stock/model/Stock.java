package com.cts.stock.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table
public class Stock {
	@Id
	private String pid;
	private int quantity;

	public Stock() {
	}

	public Stock(String pid, int quantity) {
		super();
		this.pid = pid;
		this.quantity = quantity;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock [pid=" + pid + ", quantity=" + quantity + "]";
	}

}
