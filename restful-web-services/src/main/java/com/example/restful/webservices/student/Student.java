package com.example.restful.webservices.student;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="StudentManagement")
public class Student {
	
	@Id
	@GeneratedValue
	private int Id;
	
	private String firstName;
	private String lasName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Teacher teacher;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String firstName, String lasName) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lasName = lasName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasName() {
		return lasName;
	}

	public void setLasName(String lasName) {
		this.lasName = lasName;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	@Override
	public String toString() {
		return "Student [Id=" + Id + ", firstName=" + firstName + ", lasName=" + lasName + "]";
	}
	
}
