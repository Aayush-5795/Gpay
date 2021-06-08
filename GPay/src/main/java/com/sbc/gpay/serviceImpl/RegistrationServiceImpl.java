package com.sbc.gpay.serviceImpl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.gpay.dto.RegisterRequestDto;
import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.UserBankAccountNotFoundException;
import com.sbc.gpay.exception.UserNotCreatedException;
import com.sbc.gpay.feignClient.BankClient;
import com.sbc.gpay.repository.UsersRepository;
import com.sbc.gpay.service.RegistrationService;
@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	BankClient bankClient;

	@Override
	public ResponseDto registerUser(@Valid RegisterRequestDto request) {
		
		logger.info("Inside serviceImpl registerUser() method.");
		
		ResponseDto response=new ResponseDto();
		Boolean check=bankClient.checkPhone(request.getPhone());
		
		if(!check) {
			logger.info("User Phone is not linked with Any Account..Phone="+request.getPhone());
			throw new UserBankAccountNotFoundException("User Phone is not linked with Any Account.."+request.getPhone());
		}
		
		Users newUser=new Users();
		BeanUtils.copyProperties(request, newUser);
		
		Users savedUser=usersRepository.save(newUser);
		
		response.setStatusCode(200);
		response.setStatusMessage("User created Successfully");
		
		logger.info("Exiting the serviceImpl registerUser() method ");
		return response;
	}

}
