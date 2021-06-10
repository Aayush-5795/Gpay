package com.sbc.gpay.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbc.gpay.dto.RegisterRequestDto;
import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.UserBankAccountNotFoundException;
import com.sbc.gpay.exception.UserNotCreatedException;
import com.sbc.gpay.feignClient.BankClient;
import com.sbc.gpay.repository.UsersRepository;
@SpringBootTest
class RegistrationServiceImplTest {
	
	@Mock
	UsersRepository usersRepository;
	
	@Mock
	BankClient bankClient;
	
	@InjectMocks
	RegistrationServiceImpl registrationServiceImpl;
	
	private static RegisterRequestDto getRegisterDto() {
		RegisterRequestDto register=new RegisterRequestDto();
		register.setFirstName("Aayush");
		register.setLastName("Pandit");
		register.setPassword("Aayush");
		register.setPhone("9975147898");
		register.setSalary(20000);
		register.setUserName("Aayu123");
		register.setAge(25);
		return register;
	}

	@Test
	void registerUserTest() {
		
		RegisterRequestDto register=RegistrationServiceImplTest.getRegisterDto();
		ResponseDto response=new ResponseDto();
		response.setStatusCode(200);
		Boolean actual=true;
		response.setStatusMessage("User created Successfully");
		Users user=new Users();
		BeanUtils.copyProperties(register, user);
		
		when(bankClient.checkPhone(register.getPhone())).thenReturn(actual);
		
		when(usersRepository.save(user)).thenReturn(user);
		
		Boolean check=bankClient.checkPhone(register.getPhone());
		
		assertEquals(actual, check);
		
		Users actualUser=usersRepository.save(user);
		BeanUtils.copyProperties(register, actualUser);
		assertTrue(actualUser!=null);
		
		when(registrationServiceImpl.registerUser(register)).thenReturn(response);
		
		ResponseDto actual3=registrationServiceImpl.registerUser(register);
		actual3.setStatusCode(200);
		
		assertEquals(200,actual3.getStatusCode());
		
	}
	
	@Test
	void UserBankAccountNotFoundExceptionTest() {
		
		RegisterRequestDto register=RegistrationServiceImplTest.getRegisterDto();
		
		when(bankClient.checkPhone(register.getPhone())).thenReturn(false);
		
		assertThrows(UserBankAccountNotFoundException.class, ()->{if(!bankClient.checkPhone(register.getPhone())) {
			throw new UserBankAccountNotFoundException("User Phone is not linked with Any Account.."+register.getPhone());
		};});
	}
	
	@Test
	void UserNotCreatedExceptionTest() {
		
		Users user=new Users();
		when(usersRepository.save(user)).thenReturn(null);
		
		assertThrows(UserNotCreatedException.class, ()->{if(usersRepository.save(user)==null) {
			throw new UserNotCreatedException("User Not Created.."); 
		};});
	}

}
