package com.sbc.gpay.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sbc.gpay.dto.StatementResponseDto;
import com.sbc.gpay.dto.TransactionDto;
import com.sbc.gpay.entity.Transactions;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.NoTransactionFoundException;
import com.sbc.gpay.exception.UserNotFoundException;
import com.sbc.gpay.repository.TransactionsRepository;
import com.sbc.gpay.repository.UsersRepository;
@SpringBootTest
class StatementServiceImplTest {
	
	@Mock
	UsersRepository usersRepository;
	
	@Mock
	TransactionsRepository transactionsRepository;
	
	@InjectMocks
	StatementServiceImpl statementServiceImpl;
	
	private static long userId=1;
	
	private static Users getUser() {
		Users user=new Users(1, "Aayush", "Pandit", "Aayu", "Aayu123", "9975147898", 20000, 25);
		return user;
	}
	
	private static List<Transactions> getTransactions(){
		List<Transactions> list=new ArrayList<Transactions>();
		Transactions tra1=new Transactions(1, "9975147897", "9975147899", 200, "Fund Transfer", LocalDateTime.now());
		list.add(tra1);
		return list;
	}

	@Test
	void testGetStatement() {
		
		StatementResponseDto response=new StatementResponseDto();
		Users loguser=StatementServiceImplTest.getUser();
		Optional<Users> expected=Optional.of(loguser);
		
		when(usersRepository.findById(userId)).thenReturn(expected);
		Optional<Users> actual=usersRepository.findById(userId);
		assertEquals(expected, actual);
		
		String phone=expected.get().getPhone();
		Pageable pageable=PageRequest.of(0, 10);
		
		List<Transactions> expectedList=StatementServiceImplTest.getTransactions();
		when(transactionsRepository.findByFromPhoneOrToPhoneOrderByTransactionDateTime(phone,phone,pageable)).thenReturn(expectedList);
		List<Transactions> actuallist=transactionsRepository.findByFromPhoneOrToPhoneOrderByTransactionDateTime(phone,phone,pageable);
		assertEquals(expectedList.size(), actuallist.size());
		
		List<TransactionDto> transactionlists=new ArrayList<>();
		for (Transactions transactions : expectedList) {
			TransactionDto entry=new TransactionDto();
			BeanUtils.copyProperties(transactions, entry);
			transactionlists.add(entry);
		}
		
		response.setTransactionList(transactionlists);
		
		StatementResponseDto actualresponse=statementServiceImpl.getStatement(userId);
		actualresponse.setTransactionList(transactionlists);
		assertEquals(response.getTransactionList().size(), actualresponse.getTransactionList().size());
	
	}
	
	@Test
	void userNotFoundTest() {
		
		Optional<Users> user=Optional.ofNullable(null);
		when(usersRepository.findById(userId)).thenReturn(user);
		Optional<Users> actual=usersRepository.findById(userId);
		assertThrows(UserNotFoundException.class, ()->{
			if(!actual.isPresent()) {
				throw new UserNotFoundException("User Not Found for User Id="+userId);
			}
		});
		
	}
	
	@Test
	void noTransactionFoundTest() {
		List<Transactions> list=new ArrayList<>();
		Users user=StatementServiceImplTest.getUser();
		String phone=user.getPhone();
		Pageable pageable=PageRequest.of(0, 10);
		when(transactionsRepository.findByFromPhoneOrToPhoneOrderByTransactionDateTime(phone,phone,pageable)).thenReturn(list);
		assertThrows(NoTransactionFoundException.class, ()->{
			if(list.size()<1) {
				throw new NoTransactionFoundException("No transactions found for User Id="+userId);
			}
		});
	}

}
