package com.sbc.gpay.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	TransactionsRepository transactionsRepository;

	private final ModelMapper mapper = new ModelMapper();

	@Override
	public StatementResponseDto getStatement(long userId) {

		log.info("Inside ServiceImpl getStatement() method");
		StatementResponseDto response = new StatementResponseDto();
		Optional<Users> user = usersRepository.findById(userId);
		if (!user.isPresent()) {
			log.info("User Not Found for User Id=" + userId);
			throw new UserNotFoundException("User Not Found for User Id=" + userId);
		}

		String phone = user.get().getPhone();
		Pageable pageable = PageRequest.of(0, 10);
		List<Transactions> list = transactionsRepository.findByFromPhoneOrToPhoneOrderByTransactionDateTime(phone,
				phone, pageable);
		if (list.size() < 1) {
			log.info("No transactions found for User Id=" + userId);
			throw new NoTransactionFoundException("No transactions found for User Id=" + userId);
		}

		List<TransactionDto> transactionlist = new ArrayList<>();
		for (Transactions transactions : list) {
			TransactionDto entry = mapper.map(transactions, TransactionDto.class);
			transactionlist.add(entry);
		}
		response.setTransactionList(transactionlist);
		log.info("Exiting the ServiceImpl getStatement() method");
		return response;
	}

}
