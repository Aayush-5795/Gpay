package com.sbc.gpay.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Users {
	
		@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private long id;
	    @Column
	    @NotNull
	    @NotBlank(message = "First Name is not blank.")
	    private String firstName;
	    @Column
	    private String lastName;
	    @Column
	    @NotNull
	    @NotBlank(message = "Username is not blank.")
	    private String userName;
	    @Column
	    @JsonIgnore
	    @NotNull
	    @NotBlank(message = "Password is not blank.")
	    private String password;
	    @Column
	    @NotNull
	    @NotBlank(message = "Phone is not blank.")
	    private String phone;
	    @Column
	    private double salary;
	    @Column
	    private int age;
	    
		

}
