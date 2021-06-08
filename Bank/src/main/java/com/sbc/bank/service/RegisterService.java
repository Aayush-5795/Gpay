package com.sbc.bank.service;

import javax.validation.Valid;

import com.sbc.bank.dto.RegisterRequestDto;
import com.sbc.bank.dto.ResponseDto;

public interface RegisterService {

	ResponseDto registerUser(@Valid RegisterRequestDto registerRequestDto);

	Boolean checkPone(String phone);

}
