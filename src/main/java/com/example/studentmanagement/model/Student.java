package com.example.studentmanagement.model;

public class Student {
	int id;
    String name;
    String course;
    String email;
    String ph_no;
    String address;
    
    public Student(int id, String name, String course, String email, String ph_no, String address) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.email = email;
		this.ph_no = ph_no;
		this.address = address;
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

	public String getPh_no() {
		return ph_no;
	}

	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
