package com.sbc.gpay.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.entity.Transactions;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.TransactionFailedException;
import com.sbc.gpay.exception.UnsufficientBalanceException;
import com.sbc.gpay.exception.UserNotFoundException;
import com.sbc.gpay.feignClient.BankClient;
import com.sbc.gpay.repository.TransactionsRepository;
import com.sbc.gpay.repository.UsersRepository;
import com.sbc.gpay.service.FundTransferService;
@Service
public class FundTransferServiceImpl implements FundTransferService {
	
	private static final Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	BankClient bankClient;

	@Override
	public ResponseDto fundTransferByPhone(String fromPhone, String toPhone, double amount) {
		
		logger.info("Inside ServiceImpl fundTransferByPhone() method");
		
		ResponseDto response=new ResponseDto();
		
		Optional<Users> fromuser=usersRepository.findByPhone(fromPhone);
		if(!fromuser.isPresent()) {
			logger.info("User not found for the From Phone="+fromPhone);
			throw new UserNotFoundException("User not found for the From Phone="+fromPhone);
		}
		
		Optional<Users> touser=usersRepository.findByPhone(toPhone);
		if(!touser.isPresent()) {
			logger.info("User not found for the To Phone="+toPhone);
			throw new UserNotFoundException("User not found for the To Phone="+toPhone);
		}
		
		Boolean checkAmount=bankClient.checkAmount(fromPhone, amount);
		if(!checkAmount) {
			logger.info("User not have Sufficient Balance to Transfer Fund..");
			throw new UnsufficientBalanceException("User not have Sufficient Balance to Transfer Fund..");
		}
		
		Boolean checkFundTransfer=bankClient.fundTransferByPhone(fromPhone, toPhone, amount);
		if(!checkFundTransfer) {
			logger.info("Transaction Failed..");
			throw new TransactionFailedException("Transaction Failed..");
		}
		Transactions entry=new Transactions();
		entry.setFromPhone(fromPhone);
		entry.setToPhone(toPhone);
		entry.setAmount(amount);
		entry.setRemark("Fund Transfer Successfully..");
		
		Transactions savedEntry=transactionsRepository.save(entry);
		
		response.setStatusMessage("Fund Transfer Successfully..");
		response.setStatusCode(200);
		
		logger.info("Exiting the ServiceImpl fundTransferByPhone() method");
		return response;
	}

}
