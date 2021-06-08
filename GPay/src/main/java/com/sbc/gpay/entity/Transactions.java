package com.sbc.gpay.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
@Entity
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	private String fromPhone;
	private String toPhone;
	private double amount;
	private String remark;
	@CreationTimestamp
	private LocalDateTime transactionDateTime;
	
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getFromPhone() {
		return fromPhone;
	}
	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public Transactions(long transactionId, String fromPhone, String toPhone, double amount, String remark,
			LocalDateTime transactionDateTime) {
		super();
		this.transactionId = transactionId;
		this.fromPhone = fromPhone;
		this.toPhone = toPhone;
		this.amount = amount;
		this.remark = remark;
		this.transactionDateTime = transactionDateTime;
	}
	
	public Transactions() {
		
	}

}
