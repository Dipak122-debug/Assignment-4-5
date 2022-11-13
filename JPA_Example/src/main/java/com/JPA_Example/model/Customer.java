package com.JPA_Example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CUSTOMER")
public class Customer{
	
	@Id
	@NotNull
	@Column(name="id")
	private int id;
	
	@Column(name="CUSTOMER_NAME")
	@NotNull
	@Size(max=15, message="CustomerName shouldn't have more than 15 characters")
	private String customerName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="CITY")
	private String city;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

}
