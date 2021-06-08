package com.sbc.gpay.dto;

import java.time.LocalDateTime;

public class TransactionDto {
	
	private long transactionId;
	private String fromPhone;
	private String toPhone;
	private double amount;
	private String remark;
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
	public TransactionDto(long transactionId, String fromPhone, String toPhone, double amount, String remark,
			LocalDateTime transactionDateTime) {
		super();
		this.transactionId = transactionId;
		this.fromPhone = fromPhone;
		this.toPhone = toPhone;
		this.amount = amount;
		this.remark = remark;
		this.transactionDateTime = transactionDateTime;
	}
	public TransactionDto() {
		// TODO Auto-generated constructor stub
	}
	


}
