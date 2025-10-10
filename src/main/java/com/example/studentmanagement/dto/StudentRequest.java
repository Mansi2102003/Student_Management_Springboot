package com.example.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentRequest {
	@NotBlank(message = "Enter a valid username")
	private String name;

	@NotBlank(message = "Course is required")
	private String course;

	@NotBlank(message = "Enter a valid email")
	@Email(message = "Enter a valid email")
	private String email;

	@NotNull(message = "Enter a valid phone number")
	@Min(value = 1000000000L, message = "Phone number must be 10 digits")
	@Max(value = 9999999999L, message = "Phone number must be 10 digits")
	private Long ph_no;

	@NotBlank(message = "Address is required")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getPh_no() {
		return ph_no;
	}

	public void setPh_no(Long ph_no) {
		this.ph_no = ph_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
