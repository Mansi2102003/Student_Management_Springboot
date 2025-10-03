package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.model.Student;

@Service
public class StudentServiceImplementation implements StudentService {
	
	//Autowired annotation means it tells spring to wired studenDao bean automatically
	@Autowired
	StudentDao studentDao;
	
	//It contains core business logic is written
	@Override
	public Student addStudent(Student student) {  //Take Student object as input
		studentDao.save(student);   //Call studentsdao's method to save into database
		return student;
	}
}
