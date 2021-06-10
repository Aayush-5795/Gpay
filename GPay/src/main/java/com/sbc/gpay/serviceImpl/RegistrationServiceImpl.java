package com.sbc.gpay.serviceImpl;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.gpay.dto.RegisterRequestDto;
import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.entity.Users;
import com.sbc.gpay.exception.UserAlreadyPresentException;
import com.sbc.gpay.exception.UserBankAccountNotFoundException;
import com.sbc.gpay.feignClient.BankClient;
import com.sbc.gpay.repository.UsersRepository;
import com.sbc.gpay.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	BankClient bankClient;

	private final ModelMapper mapper = new ModelMapper();

	@Override
	public ResponseDto registerUser(@Valid RegisterRequestDto request) {

		log.info("Inside serviceImpl registerUser() method.");

		ResponseDto response = new ResponseDto();
		usersRepository.findByPhone(request.getPhone()).ifPresent(
				t -> new UserAlreadyPresentException("User Already Present for Phone=" + request.getPhone()));

		if (!bankClient.checkPhone(request.getPhone())) {
			log.info("User Phone is not linked with Any Account..Phone=" + request.getPhone());
			throw new UserBankAccountNotFoundException(
					"User Phone is not linked with Any Account.." + request.getPhone());
		}

		Users newUser = mapper.map(request, Users.class);
		usersRepository.save(newUser);
		response.setStatusCode(200);
		response.setStatusMessage("User created Successfully");
		log.info("Exiting the serviceImpl registerUser() method ");
		return response;
	}

}
