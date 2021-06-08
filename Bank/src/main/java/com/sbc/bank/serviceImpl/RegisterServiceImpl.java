package com.sbc.bank.serviceImpl;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.bank.dto.RegisterRequestDto;
import com.sbc.bank.dto.ResponseDto;
import com.sbc.bank.entity.Account;
import com.sbc.bank.entity.Transaction;
import com.sbc.bank.entity.User;
import com.sbc.bank.exception.AccountCreationException;
import com.sbc.bank.exception.TransactionCreationException;
import com.sbc.bank.exception.UserCreationException;
import com.sbc.bank.repository.AccountRepository;
import com.sbc.bank.repository.TransactionRepository;
import com.sbc.bank.repository.UserRepository;
import com.sbc.bank.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	

	@Override
	public ResponseDto registerUser(@Valid RegisterRequestDto registerRequestDto) {
		
		logger.info("Inside ServiceImpl registerUser() Method");
		
		ResponseDto response=new ResponseDto();
		
		User user=new User();
		BeanUtils.copyProperties(registerRequestDto, user);
		User saveduser=userRepository.save(user);
		if(saveduser==null) {
			logger.info("User Creation Failed");
			throw new UserCreationException("User Creation Failed");
		}
		
		Account userAccount=new Account();
		userAccount.setBalance(10000.00);
		userAccount.setBranch("Ngp");
		userAccount.setUser(saveduser);
		Account savedAccount=accountRepository.save(userAccount);
		if(savedAccount==null) {
			logger.info("Account creation Failed.");
			throw new AccountCreationException("Account creation Failed.");
		}
		
		Transaction userTransaction=new Transaction();
		userTransaction.setFromAccount(savedAccount.getAccountNumber());
		userTransaction.setAmount(10000.00);
		userTransaction.setRemark("Account Opening Deposit..");
		
		Transaction savedTransaction=transactionRepository.save(userTransaction);
		if(savedTransaction==null) {
			logger.info("Transaction creation Failed.");
			throw new TransactionCreationException("Transaction creation Failed.");
		}
		
		response.setStatusCode(200);
		response.setStatusMessage("User Created Successfully...");
		logger.info("Exiting ServiceImpl registerUser() Method");
		
		return response;
	}



	@Override
	public Boolean checkPone(String phone) {
		
		logger.info("Inside ServiceImpl checkPone() method");
		
		Optional<User> user=userRepository.findByPhone(phone);
		if(user.isPresent()) {
			return true;
		}
		logger.info("Exiting ServiceImpl checkPone() method");
		return false;
	}

}
