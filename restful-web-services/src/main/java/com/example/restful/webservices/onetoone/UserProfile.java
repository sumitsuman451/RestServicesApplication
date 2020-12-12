package com.example.restful.webservices.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_profiles")
public class UserProfile {
	
	@Id
	@GeneratedValue
	@Column(name="up_id")
	private int id;
	
	@Column(name="address")
	private String address;
	
	private String gender;
	
	@Column(name="phone_number")
	private Long phoneNumber;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "userProfile")
	private OneUser oneUser;
	
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProfile(String address, String gender, Long phoneNumber) {
		super();
		this.address = address;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public OneUser getOneUser() {
		return oneUser;
	}
	public void setOneUser(OneUser oneUser) {
		this.oneUser = oneUser;
	}
	
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", address=" + address + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ "]";
	}
	
}
