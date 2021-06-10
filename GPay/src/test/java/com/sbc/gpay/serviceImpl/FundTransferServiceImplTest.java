package com.sbc.gpay.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbc.gpay.entity.Transactions;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.TransactionFailedException;
import com.sbc.gpay.exception.UnsufficientBalanceException;
import com.sbc.gpay.exception.UserNotFoundException;
import com.sbc.gpay.feignClient.BankClient;
import com.sbc.gpay.repository.TransactionsRepository;
import com.sbc.gpay.repository.UsersRepository;
@SpringBootTest
class FundTransferServiceImplTest {
	
	@Mock
	UsersRepository usersRepository;
	
	@Mock
	TransactionsRepository transactionsRepository;
	
	@Mock
	BankClient bankClient;
	
	@InjectMocks
	FundTransferServiceImpl fundTransferServiceImpl;
	
	private static String fromPhone="9975147898"; 
	private static String toPhone="9975147899"; 
	private static double amount=200;
	
	
	private static Users getUser1() {
		Users user=new Users();
		user.setFirstName("Aayush");
		user.setLastName("Pandit");
		user.setPassword("Aayush");
		user.setPhone("9975147898");
		user.setSalary(20000);
		user.setUserName("Aayu123");
		user.setAge(25);
		return user;
	}
	
	private static Users getUser2() {
		Users user=new Users();
		user.setFirstName("Akash");
		user.setLastName("Pandit");
		user.setPassword("Akash");
		user.setPhone("9975147899");
		user.setSalary(20000);
		user.setUserName("Aaku123");
		user.setAge(25);
		return user;
	}
	
	private static Transactions getTransactions() {
		Transactions entry=new Transactions();
		entry.setFromPhone(fromPhone);
		entry.setToPhone(toPhone);
		entry.setAmount(amount);
		entry.setRemark("Fund Transfer Successfully..");
		return entry;
	}

	@Test
	void userCheckTest() {
		
	    Optional<Users> user1=Optional.of(FundTransferServiceImplTest.getUser1());
		Optional<Users> user2=Optional.of(FundTransferServiceImplTest.getUser2());
		when(usersRepository.findByPhone(fromPhone)).thenReturn(user1);
		Optional<Users> actual=usersRepository.findByPhone(fromPhone);
		assertEquals(user1, actual);
		
		when(usersRepository.findByPhone(toPhone)).thenReturn(user2);
		Optional<Users> actual2=usersRepository.findByPhone(toPhone);
		assertEquals(user2, actual2);
		
		Boolean expected3=true;
		when(bankClient.checkAmount(fromPhone, amount)).thenReturn(expected3);
		Boolean actual3=bankClient.checkAmount(fromPhone, amount);
		assertEquals(expected3, actual3);
		
		Boolean expected4=true;
		when(bankClient.fundTransferByPhone(fromPhone, toPhone, amount)).thenReturn(expected4);
		Boolean actual4=bankClient.fundTransferByPhone(fromPhone, toPhone, amount);
		assertEquals(expected4, actual4);
		
		Transactions entry=FundTransferServiceImplTest.getTransactions();
		Transactions expected5=FundTransferServiceImplTest.getTransactions();
		when(transactionsRepository.save(entry)).thenReturn(expected5);
		Transactions actual5=transactionsRepository.save(entry);
		assertEquals(expected5, actual5);
		
		fundTransferServiceImpl.fundTransferByPhone(fromPhone, toPhone,amount);
		fundTransferServiceImpl.fundTransferByPhone(fromPhone,toPhone, amount); 
		
	}
	
	
	
	@Test
	void userNotFoundExceptionTest() {
		
		Optional<Users> user=Optional.ofNullable(null);
		when(usersRepository.findByPhone(fromPhone)).thenReturn(user);
		
		Optional<Users> actual=usersRepository.findByPhone(fromPhone);
		
		assertThrows(UserNotFoundException.class, ()->{if(!actual.isPresent()) {
			throw new UserNotFoundException("User not found for the From Phone="+fromPhone);
		};});
	}
	
	@Test
	void checkAmount() {
		
		Boolean expected=true;
		when(bankClient.checkAmount(fromPhone, amount)).thenReturn(expected);
		
		Boolean actual=bankClient.checkAmount(fromPhone, amount);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void unsufficientBalanceExceptionTest() {
		
		when(bankClient.checkAmount(fromPhone, amount)).thenReturn(false);
		
		Boolean actual=bankClient.checkAmount(fromPhone, amount);
		
		assertThrows(UnsufficientBalanceException.class, ()->{if(!actual) {
			throw new UnsufficientBalanceException("User not have Sufficient Balance to Transfer Fund..");
		};});
		
	}
	
	@Test
	void fundTransferByFeignTest() {
		Boolean expected=true;
		when(bankClient.fundTransferByPhone(fromPhone, toPhone, amount)).thenReturn(expected);
		
		Boolean actual=bankClient.fundTransferByPhone(fromPhone, toPhone, amount);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void fundTransferByFeignNegativeTest() {
		
		when(bankClient.fundTransferByPhone(fromPhone, toPhone, amount)).thenReturn(false);
		
		Boolean actual=bankClient.fundTransferByPhone(fromPhone, toPhone, amount);
		
		assertThrows(TransactionFailedException.class, ()->{if(!actual) {
			throw new TransactionFailedException("Transaction Failed..");
		};});
		
	}
	
	@Test
	void saveTransactionTest() {
		
		Transactions entry=FundTransferServiceImplTest.getTransactions();
		Transactions expected=FundTransferServiceImplTest.getTransactions();
		when(transactionsRepository.save(entry)).thenReturn(expected);
		
		Transactions actual=transactionsRepository.save(entry);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void saveTransactionFailTest() {
		
		Transactions entry=FundTransferServiceImplTest.getTransactions();
		Transactions expected=null;
		when(transactionsRepository.save(entry)).thenReturn(expected);
		
		Transactions actual=transactionsRepository.save(entry);
		
		assertThrows(TransactionFailedException.class, ()->{if(actual==null) {
			throw new TransactionFailedException("Transaction Failed..");
		};});
	}

}
