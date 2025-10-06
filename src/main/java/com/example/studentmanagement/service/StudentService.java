package com.example.studentmanagement.service;

import java.util.List;

import com.example.studentmanagement.model.Student;

//It is service class in which controller sends request here
// and repository is called from here to talk to the database

public interface StudentService {
	Student addStudent(Student student);
	Student getStudentById(int id);
	Student updateStudentInfo(Student student) throws Exception;
	List<Student> getAllStudents();
}
