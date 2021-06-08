package com.sbc.bank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;
	@NotNull
	private double balance;
	@NotBlank
	private String branch;
	@CreationTimestamp
	private LocalDate openingDate;
	@OneToOne
	private User user;
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public LocalDate getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Account(long accountNumber, @NotNull double balance, @NotBlank String branch, LocalDate openingDate,
			User user) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.branch = branch;
		this.openingDate = openingDate;
		this.user = user;
	}
	public Account() {
		
	}
	

}
