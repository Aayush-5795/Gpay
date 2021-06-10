package com.sbc.gpay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
public class RegisterRequestDto {
	
	  	@NotNull
	    @NotBlank(message = "First Name is not blank.")
	    private String firstName;
	    private String lastName;
	    @NotNull
	    @NotBlank(message = "Username is not blank.")
	    private String userName;
	    @NotNull
	    @NotBlank(message = "Password is not blank.")
	    private String password;
	    @NotNull
	    @NotBlank(message = "Phone is not blank.")
	    private String phone;
	    private double salary;
	    private int age;
	    
		

}
