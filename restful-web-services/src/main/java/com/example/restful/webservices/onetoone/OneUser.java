package com.example.restful.webservices.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="one_user")
public class OneUser {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int id;
	private String name;
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="up_id")
	@JsonIgnore
	private UserProfile userProfile;
	
	
	public OneUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OneUser(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	@Override
	public String toString() {
		return "OneUser [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
