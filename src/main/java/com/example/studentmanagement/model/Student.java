package com.example.studentmanagement.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Student {
	int id;
	String course;
	String email;
	String address;

	@NotNull(message = "Enter a valid phone number")
	@Min(value = 1000000000L, message = "Phone number must be 10 digits")
	@Max(value = 9999999999L, message = "Phone number must be 10 digits")
	long ph_no;

	@NotNull(message = "Enter a valid Name")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces.")
	String name;

	public Student() {

	}

	public Student(int id, String course, String email, long ph_no, String address, String name) {
		super();
		this.id = id;
		this.course = course;
		this.email = email;
		this.ph_no = ph_no;
		this.address = address;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPh_no() {
		return ph_no;
	}

	public void setPh_no(long ph_no) {
		this.ph_no = ph_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
