package com.sbc.bank.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.bank.dto.StatementResponseDto;
import com.sbc.bank.dto.TransactionDto;
import com.sbc.bank.entity.Account;
import com.sbc.bank.entity.Transaction;
import com.sbc.bank.exception.UserAccountNotFoundException;
import com.sbc.bank.repository.AccountRepository;
import com.sbc.bank.repository.TransactionRepository;
import com.sbc.bank.service.StatementService;
@Service
public class StatementServiceImpl implements StatementService {
	
	private static final Logger logger = LoggerFactory.getLogger(StatementServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public StatementResponseDto getStatement(long accountNumber) {
		
		logger.info("Inside ServiceImpl getStatement() method.");
		
		StatementResponseDto statement=new StatementResponseDto();
		
		Optional<Account> account=accountRepository.findByAccountNumber(accountNumber);
		if(!account.isPresent()) {
			logger.info("Account Not Found..For Account No.="+accountNumber);
			throw new UserAccountNotFoundException("Account Not Found..For Account No.="+accountNumber);
		}
		
		List<TransactionDto> list=new ArrayList<>();
		List<Transaction> transactions=transactionRepository.findByFromAccountOrToAccount(accountNumber,accountNumber);
		for (Transaction transaction : transactions) {
			TransactionDto entry=new TransactionDto();
			BeanUtils.copyProperties(transaction, entry);
			list.add(entry);
		}
		
		statement.setAccountNumber(accountNumber);
		statement.setTransactions(list);
		
		logger.info("Exiting ServiceImpl getStatement() method.");
		return statement;
	}
	
	

}
