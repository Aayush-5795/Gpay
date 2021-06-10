package com.sbc.gpay.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
	
	
	

}
