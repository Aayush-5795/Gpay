package com.sbc.bank.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.bank.dto.ResponseDto;
import com.sbc.bank.entity.Account;
import com.sbc.bank.entity.Transaction;
import com.sbc.bank.entity.User;
import com.sbc.bank.exception.TransactionCreationException;
import com.sbc.bank.exception.UnSufficientBalanceException;
import com.sbc.bank.exception.UpdateBalanceException;
import com.sbc.bank.exception.UserAccountNotFoundException;
import com.sbc.bank.exception.UserNotFoundException;
import com.sbc.bank.repository.AccountRepository;
import com.sbc.bank.repository.TransactionRepository;
import com.sbc.bank.repository.UserRepository;
import com.sbc.bank.service.FundTrasferService;
@Service
public class FundTrasferServiceImpl implements FundTrasferService {
	
	private static final Logger logger = LoggerFactory.getLogger(FundTrasferServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseDto fundTransfer(long fromAccount, long toAccount, double amount) {
		
		logger.info("Inside ServiceImpl fundTransfer() method");
		ResponseDto response=new ResponseDto();
		
		Optional<Account> account=accountRepository.findByAccountNumber(fromAccount);
		if(!account.isPresent()) {
			logger.info("User Account Not Found.. For Account No.="+fromAccount);
			throw new UserAccountNotFoundException("User Account Not Found.. For Account No.="+fromAccount);
		}
		
		Optional<Account> account1=accountRepository.findByAccountNumber(toAccount);
		if(!account1.isPresent()) {
			logger.info("User Account Not Found.. For Account No.="+toAccount);
			throw new UserAccountNotFoundException("User Account Not Found.. For Account No.="+toAccount);
		}
		
		Account sourceAccount=account.get();
		Account destAccount=account1.get();
		if(sourceAccount.getBalance()<amount) {
			logger.info("User have no Sufficient Balance to Transfer");
			throw new UnSufficientBalanceException("User have no Sufficient Balance to Transfer");
		}
		
		Transaction transaction=new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setAmount(amount);
		transaction.setRemark("Transaction Successful...");
		
		Transaction check=transactionRepository.save(transaction);
		if(check==null) {
			logger.info("Transaction creation Failed.");
			throw new TransactionCreationException("Transaction creation Failed.");
		}
		
		double remainingBalance=sourceAccount.getBalance()-amount;
		sourceAccount.setBalance(remainingBalance);
		
		double updatedBalance=destAccount.getBalance()+amount;
		destAccount.setBalance(updatedBalance);
		
		Account updatedSourceAccount=accountRepository.save(sourceAccount);
		Account updatedToAccount=accountRepository.save(destAccount);
		
		if(updatedSourceAccount.getBalance()!=remainingBalance) {
			logger.info("Unable to update Balance..For Account No.="+fromAccount);
			throw new UpdateBalanceException("Unable to update Balance..For Account No.="+fromAccount);
		}
		
		if(updatedToAccount.getBalance()!=updatedBalance) {
			logger.info("Unable to update Balance..For Account No.="+toAccount);
			throw new UpdateBalanceException("Unable to update Balance..For Account No.="+toAccount);
		}
		
		response.setStatusCode(200);
		response.setStatusMessage("Fund Transfer Successfully..");
		logger.info("Exiting ServiceImpl fundTransfer() method");
		return response;
	}

	@Override
	public Boolean checkAmount(String fromPhone, double amount) {
		
		logger.info("Inside ServiceImpl checkAmount() method");
		Optional<User> user=userRepository.findByPhone(fromPhone);
		if(!user.isPresent()) {
			logger.info("User not Found.. For Phone ="+fromPhone);
			throw new UserNotFoundException("User not Found.. For Phone ="+fromPhone);
		}
		
		Optional<Account> userAccount=accountRepository.findByUser(user.get());
		if(!userAccount.isPresent()) {
			logger.info("User Account Not Found..For Phone ="+fromPhone);
			throw new UserAccountNotFoundException("User Account Not Found..For Phone ="+fromPhone);
		}
		
		if(userAccount.get().getBalance()<amount) {
			return false;
		}
		logger.info("Exiting ServiceImpl checkAmount() method");
		return true;
	}

	@Override
	public Boolean fundTransferByPhone(String fromPhone, String toPhone, double amount) {
		
		logger.info("Inside ServiceImpl fundTransferByPhone() method");
		
		Optional<User> fromuser=userRepository.findByPhone(fromPhone);
		if(!fromuser.isPresent()) {
			logger.info("User not Found.. For Phone ="+fromPhone);
			throw new UserNotFoundException("User not Found.. For Phone ="+fromPhone);
		}
		
		Optional<User> touser=userRepository.findByPhone(toPhone);
		if(!touser.isPresent()) {
			logger.info("User not Found.. For Phone ="+toPhone);
			throw new UserNotFoundException("User not Found.. For Phone ="+toPhone);
		}
		
		Optional<Account> fromAccount=accountRepository.findByUser(fromuser.get());
		if(!fromAccount.isPresent()) {
			logger.info("User Account Not Found..");
			throw new UserAccountNotFoundException("User Account Not Found..");
		}
		
		Optional<Account> toAccount=accountRepository.findByUser(touser.get());
		if(!toAccount.isPresent()) {
			logger.info("User Account Not Found..");
			throw new UserAccountNotFoundException("User Account Not Found..");
		}
		
		ResponseDto response=fundTransfer(fromAccount.get().getAccountNumber(),toAccount.get().getAccountNumber(),amount);
		if(response.getStatusCode()!=200) {
			return false;
		}
		logger.info("Exiting ServiceImpl fundTransferByPhone() method");
		return true;
	}

}
