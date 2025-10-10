package com.example.studentmanagement.model;


public class Student {
	int id;
	String course;
	String email;
	String address;
	long ph_no;

	
	String name;

	public Student() {

	}

	public Student(int id, String course, String email, String address, long ph_no, String name) {
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
