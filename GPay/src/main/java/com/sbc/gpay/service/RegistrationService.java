package com.sbc.gpay.service;

import javax.validation.Valid;

import com.sbc.gpay.dto.RegisterRequestDto;
import com.sbc.gpay.dto.ResponseDto;

public interface RegistrationService {

	ResponseDto registerUser(@Valid RegisterRequestDto request);

}
