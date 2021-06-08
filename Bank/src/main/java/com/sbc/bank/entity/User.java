package com.sbc.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


@Entity
public class User {
	
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
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
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
		public User(long id, @NotBlank(message = "First Name is not blank.") String firstName, String lastName,
				@NotBlank(message = "Username is not blank.") String username,
				@NotBlank(message = "Password is not blank.") String password,
				@NotBlank(message = "Phone is not blank.") String phone, double salary, int age) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = username;
			this.password = password;
			this.phone = phone;
			this.salary = salary;
			this.age = age;
		}
	    
		public User() {
			
		}

}
