package com.sbc.bank.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;
@Validated
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
	    
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getUsername() {
			return userName;
		}
		public void setUsername(String username) {
			this.userName = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	    
	    

}
