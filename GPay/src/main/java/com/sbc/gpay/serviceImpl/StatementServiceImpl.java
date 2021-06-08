package com.sbc.gpay.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sbc.gpay.dto.StatementResponseDto;
import com.sbc.gpay.dto.TransactionDto;
import com.sbc.gpay.entity.Transactions;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.NoTransactionFoundException;
import com.sbc.gpay.exception.UserNotFoundException;
import com.sbc.gpay.repository.TransactionsRepository;
import com.sbc.gpay.repository.UsersRepository;
import com.sbc.gpay.service.StatementService;
@Service
public class StatementServiceImpl implements StatementService {
	
	private static final Logger logger = LoggerFactory.getLogger(StatementServiceImpl.class);
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	TransactionsRepository transactionsRepository;

	@Override
	public StatementResponseDto getStatement(long userId) {
		
		logger.info("Inside ServiceImpl getStatement() method");
		
		StatementResponseDto response=new StatementResponseDto();
		
		Optional<Users> user=usersRepository.findById(userId);
		if(!user.isPresent()) {
			logger.info("User Not Found for User Id="+userId);
			throw new UserNotFoundException("User Not Found for User Id="+userId);
		}
		
		String phone=user.get().getPhone();
		Pageable pageable=PageRequest.of(0, 10);
		List<Transactions> list=transactionsRepository.findByFromPhoneOrToPhoneOrderByTransactionDateTime(phone,phone,pageable);
		if(list.size()<1) {
			logger.info("No transactions found for User Id="+userId);
			throw new NoTransactionFoundException("No transactions found for User Id="+userId);
		}
		
		List<TransactionDto> transactionlist=new ArrayList<>();
		for (Transactions transactions : list) {
			TransactionDto entry=new TransactionDto();
			BeanUtils.copyProperties(transactions, entry);
			transactionlist.add(entry);
		}
		
		response.setTransactionList(transactionlist);
		
		logger.info("Exiting the ServiceImpl getStatement() method");
		
		return response;
	}

}
