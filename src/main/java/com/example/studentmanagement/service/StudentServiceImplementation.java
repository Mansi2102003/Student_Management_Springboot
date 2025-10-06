package com.example.studentmanagement.service;

import java.util.List;

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
	
	@Override
	public Student getStudentById(int id) {
		return studentDao.findById(id);
	}
	
	@Override
	public Student updateStudentInfo(Student student) throws Exception{ 
		int updated =studentDao.updateStudent(student);
		if(updated ==0) {
			throw new Exception("Student not found with" + student.getId()+" " );
		}
		return student;
	}
	
	@Override
	public List<Student> getAllStudents(){
		return studentDao.findAll();
	}
	
}
