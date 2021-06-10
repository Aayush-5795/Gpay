package com.sbc.gpay.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	
	private long transactionId;
	private String fromPhone;
	private String toPhone;
	private double amount;
	private String remark;
	private LocalDateTime transactionDateTime;
}
