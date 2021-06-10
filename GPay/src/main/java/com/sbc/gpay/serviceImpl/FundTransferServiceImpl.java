package com.sbc.gpay.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.gpay.entity.Transactions;

import com.sbc.gpay.exception.FromAndToPhoneCanNotBeSameException;
import com.sbc.gpay.exception.TransactionFailedException;
import com.sbc.gpay.exception.UnsufficientBalanceException;
import com.sbc.gpay.exception.UserNotFoundException;
import com.sbc.gpay.feignClient.BankClient;
import com.sbc.gpay.repository.TransactionsRepository;
import com.sbc.gpay.repository.UsersRepository;
import com.sbc.gpay.service.FundTransferService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FundTransferServiceImpl implements FundTransferService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	BankClient bankClient;

	@Override
	public void fundTransferByPhone(String fromPhone, String toPhone, double amount) {
		
		log.info("Inside ServiceImpl fundTransferByPhone() method");
		if(fromPhone.equals(toPhone)) {
			log.info("From and To phone Can't be same..");
			throw new FromAndToPhoneCanNotBeSameException("From and To phone Can't be same..");
		}
		
		usersRepository.findByPhone(fromPhone).orElseThrow(()-> new UserNotFoundException("User not found for the From Phone="+fromPhone));
		usersRepository.findByPhone(toPhone).orElseThrow(()-> new UserNotFoundException("User not found for the To Phone="+toPhone));
		if(!bankClient.checkAmount(fromPhone, amount)) {
			log.info("User not have Sufficient Balance to Transfer Fund..");
			throw new UnsufficientBalanceException("User not have Sufficient Balance to Transfer Fund..");
		}
		
		if(!bankClient.fundTransferByPhone(fromPhone, toPhone, amount)) {
			log.info("Transaction Failed..");
			throw new TransactionFailedException("Transaction Failed..");
		}
		Transactions entry=Transactions.builder().fromPhone(fromPhone).toPhone(toPhone).amount(amount).remark("Fund Transfer Successfully..").build();
		
		transactionsRepository.save(entry);
		log.info("Exiting the ServiceImpl fundTransferByPhone() method");
		
	}

}
